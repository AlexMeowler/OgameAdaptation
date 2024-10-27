import {Component, OnDestroy} from '@angular/core';
import {DecimalPipe, KeyValuePipe, NgForOf, NgIf, NgOptimizedImage, NgTemplateOutlet} from "@angular/common";
import {CustomNumberPipe} from "../pipes/CustomNumberPipe";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {Subscription, switchMap} from "rxjs";
import {User} from "../model/User";
import {UserService} from "../services/user.service";
import {ResourceService} from "../services/resource.service";
import {DurationPipe} from "../pipes/DurationPipe";
import {OrderService} from "../services/order.service";
import {UnitService} from "../services/unit.service";
import {UnitDetails} from "../model/UnitDetails";
import {TooltipDirective} from "./tooltip/tooltip.directive";

@Component({
    selector: 'unit-info',
    standalone: true,
    imports: [
        CustomNumberPipe,
        NgOptimizedImage,
        NgIf,
        KeyValuePipe,
        NgForOf,
        NgTemplateOutlet,
        DurationPipe,
        TooltipDirective
    ],
    templateUrl: '../../templates/unit-details-page.html',
    styleUrl: '../../styles/styles.scss',
    providers: [DecimalPipe]
})
export class UnitDetailsComponent implements OnDestroy {

    unit!: UnitDetails

    userSubscription: Subscription
    user!: User

    private routeParamsSubscription?: Subscription;

    constructor(private unitService: UnitService,
                private userService: UserService,
                private resourceService: ResourceService,
                private orderService: OrderService,
                private activateRoute: ActivatedRoute) {

        this.userSubscription = this.userService.getUserInfo().subscribe({
            next: (data?: User) => {
                if (data) {
                    this.user = data
                    this.initBuildingDetails(activateRoute, unitService)
                }
            }
        })
    }

    private initBuildingDetails(activateRoute: ActivatedRoute, unitService: UnitService) {
        this.routeParamsSubscription = activateRoute.paramMap
            .pipe(switchMap((params: ParamMap) => unitService.getUnitDetails(this.user.activePlanet, Number(params.get("id")))))
            .subscribe({
                next: (data: UnitDetails) => {
                    this.unit = data
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
