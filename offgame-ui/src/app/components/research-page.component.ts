import {Component, OnDestroy} from '@angular/core';
import {PlanetService} from "../services/planet.service";
import {DatePipe, DecimalPipe, NgForOf, NgIf, NgOptimizedImage, NgTemplateOutlet} from "@angular/common";
import {TooltipDirective} from "./tooltip/tooltip.directive";
import {CustomNumberPipe} from "../pipes/CustomNumberPipe";
import {DurationPipe} from "../pipes/DurationPipe";
import {ENERGY_DIFF_NEGATIVE_TOOLTIP, ENERGY_DIFF_POSITIVE_TOOLTIP} from "../app.config";
import {Resources} from "../model/resource/Resources";
import {Resource} from "../model/resource/Resource";
import {ResourceService} from "../services/resource.service";
import {Subscription} from "rxjs";
import {RouterLink} from "@angular/router";
import {User} from "../model/User";
import {UserService} from "../services/user.service";
import {TechnologyInstance} from "../model/TechnologyInstance";

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
    //buildingOrders: BuildingOrder[] = [] //TODO research button
    currentBuildTimer!: number

    resourcesSubscription!: Subscription
    resources!: Resources

    userSubscription!: Subscription
    user!: User

    constructor(private planetService: PlanetService,
                private resourceService: ResourceService,
                private userService: UserService) {

        this.userSubscription = this.userService.getUserInfo().subscribe({
            next: (data?: User) => {
                if (data) {
                    this.user = data;
                    this.updatePlanetTechnologies();
                    this.resourceService.updateResources(this.user.activePlanet);
                    this.resourcesSubscription = this.initResourceSubscription();
                }
            }
        })
    }

    private updatePlanetTechnologies() {
        this.planetService.getPlanetTechnologies(this.user.activePlanet).subscribe({
            next: (data: TechnologyInstance[]) => this.technologyInstances = data
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
        this.resourcesSubscription.unsubscribe();
        this.userSubscription.unsubscribe();
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

    protected readonly ENERGY_DIFF_POSITIVE_TOOLTIP = ENERGY_DIFF_POSITIVE_TOOLTIP;
    protected readonly ENERGY_DIFF_NEGATIVE_TOOLTIP = ENERGY_DIFF_NEGATIVE_TOOLTIP;
}
