import {Component, OnDestroy} from '@angular/core';
import {DecimalPipe, KeyValuePipe, NgForOf, NgIf, NgOptimizedImage, NgTemplateOutlet} from "@angular/common";
import {CustomNumberPipe} from "../pipes/CustomNumberPipe";
import {BuildingService} from "../services/building.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {Subscription, switchMap} from "rxjs";
import {BuildingDetails} from "../model/BuildingDetails";
import {TYPE_CRYSTAL, TYPE_DEUTERIUM, TYPE_ENERGY, TYPE_METAL} from "../model/resource/ResourceContext";
import {Resources} from "../model/resource/Resources";
import {Resource} from "../model/resource/Resource";
import {User} from "../model/User";
import {UserService} from "../services/user.service";

@Component({
    selector: 'building-info',
    standalone: true,
    imports: [
        CustomNumberPipe,
        NgOptimizedImage,
        NgIf,
        KeyValuePipe,
        NgForOf,
        NgTemplateOutlet
    ],
    templateUrl: '../../templates/building-details-page.html',
    styleUrl: '../../styles/styles.scss',
    providers: [DecimalPipe]
})
export class BuildingDetailsComponent implements OnDestroy {

    building!: BuildingDetails

    userSubscription: Subscription
    user!: User

    private routeParamsSubscription?: Subscription;

    constructor(private buildingService: BuildingService,
                private userService: UserService,
                private activateRoute: ActivatedRoute) {

        this.userSubscription = this.userService.getUserInfo().subscribe({
            next: (data?: User) => {
                if (data) {
                    this.user = data
                    this.initBuildingDetails(activateRoute, buildingService)
                }
            }
        })


    }

    private initBuildingDetails(activateRoute: ActivatedRoute, buildingService: BuildingService) {
        this.routeParamsSubscription = activateRoute.paramMap
            .pipe(switchMap((params: ParamMap) => buildingService.getBuildingDetails(this.user.activePlanet, Number(params.get("id")))))
            .subscribe({
                next: (data: BuildingDetails) => {
                    this.building = data
                }
            });
    }

    isResourceAffected(resourceName: string): number {
        let getter: (resource: Resources) => Resource;
        switch (resourceName) {
            case TYPE_METAL:
                getter = (res: Resources) => res.metal;
                break;
            case TYPE_CRYSTAL:
                getter = (res: Resources) => res.crystal;
                break;
            case TYPE_DEUTERIUM:
                getter = (res: Resources) => res.deuterium;
                break;
            case TYPE_ENERGY:
                getter = (res: Resources) => res.energy;
                break;
            default:
                return 0;
        }

        return this.building.isResourceAffected(getter);
    }

    getColor(condition: boolean): string {
        return condition ? 'lime' : 'red'
    }

    ngOnDestroy(): void {
        this.userSubscription.unsubscribe()
        this.routeParamsSubscription?.unsubscribe()
    }
}
