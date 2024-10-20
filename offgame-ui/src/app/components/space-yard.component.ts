import {Component, OnDestroy} from '@angular/core';
import {PlanetService} from "../services/planet.service";
import {DatePipe, DecimalPipe, NgForOf, NgIf, NgOptimizedImage, NgTemplateOutlet} from "@angular/common";
import {TooltipDirective} from "./tooltip/tooltip.directive";
import {CustomNumberPipe} from "../pipes/CustomNumberPipe";
import {DurationPipe} from "../pipes/DurationPipe";
import {Resources} from "../model/resource/Resources";
import {Resource} from "../model/resource/Resource";
import {OrderService} from "../services/order.service";
import {ResourceService} from "../services/resource.service";
import {Subscription} from "rxjs";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {User} from "../model/User";
import {UserService} from "../services/user.service";
import {UnitInstance} from "../model/UnitInstance";
import {DomSanitizer} from "@angular/platform-browser";
import {UnitOrder} from "../model/UnitOrder";
import {FormsModule} from "@angular/forms";

@Component({
    selector: 'space-yard-page',
    standalone: true,
    imports: [
        NgForOf,
        NgIf,
        TooltipDirective,
        NgOptimizedImage,
        CustomNumberPipe,
        NgTemplateOutlet,
        DurationPipe,
        DatePipe,
        RouterLink,
        FormsModule
    ],
    templateUrl: '../../templates/space-yard-page.html',
    styleUrl: '../../styles/styles.scss',
    providers: [DecimalPipe]
})
export class SpaceYardComponent implements OnDestroy {

    typeSubscription: Subscription
    type!: string;

    unitInstances: UnitInstance[] = []
    amountInputs!: number[]

    unitOrders: UnitOrder[] = []
    currentBuildTimer!: number
    totalTimeLeft!:number

    resourcesSubscription!: Subscription
    resources!: Resources

    userSubscription!: Subscription
    user!: User

    constructor(private route: ActivatedRoute,
                private sanitizer: DomSanitizer,
                private planetService: PlanetService,
                private resourceService: ResourceService,
                private userService: UserService,
                private orderService: OrderService) {

        this.typeSubscription = this.route.data.subscribe({
            next: (data: any) => {
                this.type = data.type;
                this.initUserSubscription();
            }
        })

    }

    private initUserSubscription() {
        this.userSubscription = this.userService.getUserInfo().subscribe({
            next: (data?: User) => {
                if (data) {
                    this.user = data;
                    this.updatePlanetUnits();
                    this.resourceService.updateResources(this.user.activePlanet);
                    this.resourcesSubscription = this.initResourceSubscription();
                    this.refreshOrders()
                }
            }
        })
    }

    private updatePlanetUnits() {
        this.planetService.getPlanetUnits(this.user.activePlanet, this.type).subscribe({
            next: (data: UnitInstance[]) => {
                this.unitInstances = data
                this.amountInputs = Array(this.unitInstances.length).fill(0)
                this.unitInstances.forEach(ui => {
                    ui.requirements = ui.requirements.filter(req => req.requiredLevel > req.currentLevel)
                    ui.unit.shortDescriptionHtml = this.sanitizer.bypassSecurityTrustHtml(ui.unit.shortDescription)
                })
            }
        })
    }

    private initResourceSubscription() {
        return this.resourceService.getPlanetResources(this.user.activePlanet).subscribe({
            next: (data: Resources) => {
                this.resources = data;
            }
        })
    }

    ngOnDestroy(): void {
        this.typeSubscription.unsubscribe();
        this.resourcesSubscription.unsubscribe();
        this.userSubscription.unsubscribe();
    }

    createOrder(unitId: number, index:number) {
        this.orderService.createUnitOrder(unitId, this.user.activePlanet, this.amountInputs[index]).subscribe({
            next: ignore => {
                this.refreshOrders();
                this.resourceService.updateResources(this.user.activePlanet)
            }
        })
        this.amountInputs[index] = 0
    }

    deleteLatestOrder() {
        this.orderService.deleteUnitOrder(this.unitOrders[this.unitOrders.length - 1].id).subscribe({
            next: ignore => this.refreshOrders()
        })
    }

    canBuild(unitInstance: UnitInstance): boolean {
        let cost = unitInstance.buildingCost
        let resources = this.resources;
        return this.hasResource(cost.metal, resources.metal)
            && this.hasResource(cost.crystal, resources.crystal)
            && this.hasResource(cost.deuterium, resources.deuterium)
            && this.hasResource(cost.energy, resources.energy)
    }

    hasResource(cost: Resource, resource: Resource): boolean {
        return cost.amount <= Math.max(0, resource.amount)
    }

    getColor(condition: boolean): string {
        return condition ? 'lime' : 'red'
    }

    private refreshOrders() {
        this.orderService.getActiveUnitOrders(this.user.activePlanet).subscribe({
            next: (data: UnitOrder[]) => {
                clearInterval(this.currentBuildTimer)
                this.unitOrders = data

                if (this.unitOrders.length > 0) {
                    this.currentBuildTimer = setInterval(this.updateBuildingTimer(this.unitOrders[0]), 1000)
                    this.totalTimeLeft = this.unitOrders[0].timeLeft + this.unitOrders[0].singleUnitDuration * (this.unitOrders[0].amountLeft - 1)
                    for (let i = 1; i < this.unitOrders.length; i++) {
                        this.totalTimeLeft += this.unitOrders[i].singleUnitDuration * this.unitOrders[i].amountLeft
                    }
                }
            }
        })
    }

    private updateBuildingTimer(order: UnitOrder) {
        return () => {
            if (order.timeLeft <= 0) {
                order.amountLeft--
                if (order.amountLeft <= 0) {
                    clearInterval(this.currentBuildTimer)
                    this.refreshOrders()
                }

                order.timeLeft = order.singleUnitDuration
                this.updatePlanetUnits();
            }

            order.timeLeft--
            this.totalTimeLeft--
        }
    }
}
