import {Component, OnDestroy} from '@angular/core';
import {PlanetService} from "../services/planet.service";
import {DatePipe, DecimalPipe, NgForOf, NgIf, NgOptimizedImage, NgTemplateOutlet} from "@angular/common";
import {TooltipDirective} from "./tooltip/tooltip.directive";
import {BuildingInstance} from "../model/BuildingInstance";
import {CustomNumberPipe} from "../pipes/CustomNumberPipe";
import {DurationPipe} from "../pipes/DurationPipe";
import {Subscription} from "rxjs";
import {RouterLink} from "@angular/router";
import {User} from "../model/User";
import {UserService} from "../services/user.service";
import {TechnologyInstance} from "../model/TechnologyInstance";
import {UnitInstance} from "../model/UnitInstance";

@Component({
    selector: 'requirements-page',
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
    templateUrl: '../../templates/requirements-page.html',
    styleUrl: '../../styles/styles.scss',
    providers: [DecimalPipe]
})
export class RequirementsComponent implements OnDestroy {

    buildingInstances: BuildingInstance[] = []
    technologyInstances: TechnologyInstance[] = []
    fleetInstances: UnitInstance[] = []
    defenseInstances: UnitInstance[] = []

    userSubscription: Subscription
    user!: User

    constructor(private planetService: PlanetService,
                private userService: UserService) {

        this.userSubscription = this.userService.getUserInfo().subscribe({
            next: (data?: User) => {
                if (data) {
                    this.user = data;
                    this.updatePlanetBuildings();
                    this.updatePlanetTechnologies();
                    this.updatePlanetUnits();
                }
            }
        })
    }

    private updatePlanetBuildings() {
        this.planetService.getPlanetBuildings(this.user.activePlanet).subscribe({
            next: (data: BuildingInstance[]) => this.buildingInstances = data
        })
    }

    private updatePlanetTechnologies() {
        this.planetService.getPlanetTechnologies(this.user.activePlanet).subscribe({
            next: (data: TechnologyInstance[]) => this.technologyInstances = data
        })
    }

    private updatePlanetUnits() {
        this.planetService.getPlanetUnits(this.user.activePlanet, PlanetService.FLEET_TYPE).subscribe({
            next: (data: UnitInstance[]) => this.fleetInstances = data
        })
        this.planetService.getPlanetUnits(this.user.activePlanet, PlanetService.DEFENSE_TYPE).subscribe({
            next: (data: UnitInstance[]) => this.defenseInstances = data
        })
    }

    ngOnDestroy(): void {
        this.userSubscription.unsubscribe();
    }
}
