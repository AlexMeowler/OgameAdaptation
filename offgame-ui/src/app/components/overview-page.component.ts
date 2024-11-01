import {Component, OnDestroy} from '@angular/core';
import {DatePipe, DecimalPipe, KeyValuePipe, NgForOf, NgIf, NgOptimizedImage, NgTemplateOutlet} from "@angular/common";
import {CustomNumberPipe} from "../pipes/CustomNumberPipe";
import {Subscription} from "rxjs";
import {User} from "../model/User";
import {UserService} from "../services/user.service";
import {DurationPipe} from "../pipes/DurationPipe";
import {PlanetOverview} from "../model/PlanetOverview";
import {PlanetService} from "../services/planet.service";
import {TooltipDirective} from "./tooltip/tooltip.directive";
import {TechnologyOrder} from "../model/TechnologyOrder";
import {RouterLink} from "@angular/router";
import {BuildingOrder} from "../model/BuildingOrder";
import {UtilService} from "../services/util.service";

@Component({
    selector: 'overview-page',
    standalone: true,
    imports: [
        CustomNumberPipe,
        NgOptimizedImage,
        NgIf,
        KeyValuePipe,
        NgForOf,
        NgTemplateOutlet,
        DurationPipe,
        TooltipDirective,
        DatePipe,
        RouterLink
    ],
    templateUrl: '../../templates/overview-page.html',
    styleUrl: '../../styles/styles.scss',
    providers: [DecimalPipe]
})
export class OverviewPageComponent implements OnDestroy {

    overview!: PlanetOverview

    serverTimer!: number
    currentBuildTimer!: number
    currentResearchTimer!: number
    spaceYardTimer!: number

    userSubscription: Subscription
    user!: User

    constructor(private userService: UserService,
                private planetService: PlanetService) {

        this.userSubscription = this.userService.getUserInfo().subscribe({
            next: (data?: User) => {
                if (data) {
                    this.user = data
                    this.initPlanetOverview()
                }
            }
        })
    }

    private initPlanetOverview() {
        this.planetService.getPlanetOverview(this.user.activePlanet)
            .subscribe({
                next: (data: PlanetOverview) => {
                    this.overview = data
                    this.initTimers()
                }
            });
    }

    private initTimers() {
        this.serverTimer = setInterval(this.updateServerTime(this.overview), 1000)
        if (this.overview.activeBuildingOrder) {
            this.currentResearchTimer = setInterval(this.updateBuildTimer(this.overview.activeBuildingOrder), 1000)

        }
        if (this.overview.activeTechnologyOrder) {
            this.currentResearchTimer = setInterval(this.updateResearchTimer(this.overview.activeTechnologyOrder), 1000)

        }
        if (this.overview.spaceYardTotalTimeLeft) {
            this.spaceYardTimer = setInterval(this.updateSpaceYardTimer(this.overview), 1000)
        }
    }

    private updateServerTime(overview: PlanetOverview) {
        return () => this.overview.updateServerTime()
    }

    private updateBuildTimer(order: BuildingOrder) {
        return () => {
            if (order.timeLeft <= 0) {
                order.timeLeft = 0
                clearInterval(this.currentBuildTimer)
            }

            order.timeLeft--
        }
    }

    private updateResearchTimer(order: TechnologyOrder) {
        return () => {
            if (order.timeLeft <= 0) {
                order.timeLeft = 0
                clearInterval(this.currentResearchTimer)
            }

            order.timeLeft--
        }
    }

    private updateSpaceYardTimer(overview: PlanetOverview) {
        return () => {
            if (overview.spaceYardTotalTimeLeft) {
                if (overview.spaceYardTotalTimeLeft <= 0) {
                    overview.spaceYardTotalTimeLeft = 0
                    clearInterval(this.spaceYardTimer)
                }
                console.log(overview.spaceYardTotalTimeLeft)
                overview.spaceYardTotalTimeLeft--
            }
        }
    }

    ngOnDestroy(): void {
        this.userSubscription.unsubscribe()
        clearInterval(this.currentResearchTimer);
        clearInterval(this.spaceYardTimer);
        clearInterval(this.serverTimer);
    }

    protected readonly UtilService = UtilService;
}
