import {Component, OnDestroy} from '@angular/core';
import {DecimalPipe, NgForOf, NgOptimizedImage} from "@angular/common";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {Subscription, switchMap} from "rxjs";
import {User} from "../model/User";
import {UserService} from "../services/user.service";
import {TechnologyService} from "../services/technology.service";
import {TechnologyDetails} from "../model/TechnologyDetails";

@Component({
    selector: 'research-info',
    standalone: true,
    imports: [
        NgOptimizedImage,
        NgForOf
    ],
    templateUrl: '../../templates/research-details-page.html',
    styleUrl: '../../styles/styles.scss',
    providers: [DecimalPipe]
})
export class ResearchDetailsComponent implements OnDestroy {

    technology!: TechnologyDetails

    userSubscription: Subscription
    user!: User

    private routeParamsSubscription?: Subscription;

    constructor(private technologyService: TechnologyService,
                private userService: UserService,
                private activateRoute: ActivatedRoute) {

        this.userSubscription = this.userService.getUserInfo().subscribe({
            next: (data?: User) => {
                if (data) {
                    this.user = data
                    this.initTechnologyDetails()
                }
            }
        })


    }

    private initTechnologyDetails() {
        this.routeParamsSubscription = this.activateRoute.paramMap
            .pipe(switchMap((params: ParamMap) => this.technologyService.getTechnologyDetails(this.user.activePlanet, Number(params.get("id")))))
            .subscribe({
                next: (data: TechnologyDetails) => {
                    this.technology = data
                }
            });
    }

    getColor(condition: boolean): string {
        return condition ? 'lime' : 'red'
    }

    ngOnDestroy(): void {
        this.userSubscription.unsubscribe()
        this.routeParamsSubscription?.unsubscribe()
    }
}
