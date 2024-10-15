import {Component, OnDestroy} from '@angular/core';
import {PlanetService} from "../services/planet.service";
import {DatePipe, DecimalPipe, NgForOf, NgIf, NgOptimizedImage, NgTemplateOutlet} from "@angular/common";
import {TooltipDirective} from "./tooltip/tooltip.directive";
import {CustomNumberPipe} from "../pipes/CustomNumberPipe";
import {DurationPipe} from "../pipes/DurationPipe";
import {Resources} from "../model/resource/Resources";
import {Resource} from "../model/resource/Resource";
import {ResourceService} from "../services/resource.service";
import {Subscription} from "rxjs";
import {RouterLink} from "@angular/router";
import {User} from "../model/User";
import {UserService} from "../services/user.service";
import {TechnologyInstance} from "../model/TechnologyInstance";
import {TechnologyOrder} from "../model/TechnologyOrder";
import {OrderService} from "../services/order.service";
import {PlanetItem} from "../model/PlanetItem";

@Component({
    selector: 'research-page',
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
        RouterLink
    ],
    templateUrl: '../../templates/research-page.html',
    styleUrl: '../../styles/styles.scss',
    providers: [DecimalPipe]
})
export class ResearchComponent implements OnDestroy {

    technologyInstances: TechnologyInstance[] = []
    technologyOrder?: TechnologyOrder
    currentResearchTimer!: number

    resourcesSubscription!: Subscription
    resources!: Resources

    userSubscription!: Subscription
    user!: User

    planetNamesSubscription: Subscription
    planetNames: Map<number, string> = new Map<number, string>()

    constructor(private planetService: PlanetService,
                private resourceService: ResourceService,
                private userService: UserService,
                private orderService: OrderService) {

        this.userSubscription = this.userService.getUserInfo().subscribe({
            next: (data?: User) => {
                if (data) {
                    this.user = data;
                    this.updatePlanetTechnologies();
                    this.resourceService.updateResources(this.user.activePlanet);
                    this.resourcesSubscription = this.initResourceSubscription();
                    this.getOrder();
                }
            }
        })

        this.planetNamesSubscription = this.userService.getPlanetList().subscribe({
            next: (data: PlanetItem[]) => {
                this.planetNames = new Map(data.map(i => [i.id, i.name]))
            }
        })
    }

    private updatePlanetTechnologies() {
        this.planetService.getPlanetTechnologies(this.user.activePlanet).subscribe({
            next: (data: TechnologyInstance[]) => {
                this.technologyInstances = data;
                this.technologyInstances.forEach(ti => {
                    ti.requirements = ti.requirements.filter(req => req.requiredLevel >= req.currentLevel)
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

    private getOrder() {
        this.orderService.getTechnologyOrder(this.user.activePlanet).subscribe({
            next: (data: TechnologyOrder | null) => {
                this.technologyOrder = undefined;
                clearInterval(this.currentResearchTimer);

                if (data) {
                    this.technologyOrder = data;
                    this.currentResearchTimer = setInterval(this.updateBuildingTimer(this.technologyOrder), 1000);
                }
            }
        })
    }

    private updateBuildingTimer(order: TechnologyOrder) {
        return () => {
            if (order.timeLeft <= 0) {
                clearInterval(this.currentResearchTimer);
                this.getOrder();
            }

            order.timeLeft--;
        }
    }

    createOrder(technologyId: number) {
        this.orderService.createTechnologyOrder(technologyId, this.user.activePlanet).subscribe({
            next: ignore => {
                this.getOrder();
                this.resourceService.updateResources(this.user.activePlanet)
            }
        })
    }

    deleteOrder(orderId: number) {
        this.orderService.deleteTechnologyOrder(orderId).subscribe({
            next: ignore => this.getOrder()
        })
    }

    ngOnDestroy(): void {
        this.resourcesSubscription.unsubscribe();
        this.userSubscription.unsubscribe();
        this.planetNamesSubscription.unsubscribe();
    }

    canBuild(technologyInstance: TechnologyInstance): boolean {
        let cost = technologyInstance.researchCost
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
}
