import {Component, OnDestroy} from '@angular/core';
import {DatePipe, DecimalPipe, KeyValuePipe, NgForOf, NgIf, NgOptimizedImage, NgTemplateOutlet} from "@angular/common";
import {TooltipDirective} from "./tooltip/tooltip.directive";
import {CustomNumberPipe} from "../pipes/CustomNumberPipe";
import {DurationPipe} from "../pipes/DurationPipe";
import {BuildingService} from "../services/building.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {Subscription, switchMap} from "rxjs";
import {BuildingDetails} from "../model/BuildingDetails";
import {TYPE_CRYSTAL, TYPE_DEUTERIUM, TYPE_ENERGY, TYPE_METAL} from "../model/resource/ResourceContext";
import {Resources} from "../model/resource/Resources";
import {Resource} from "../model/resource/Resource";

@Component({
    selector: 'building-info',
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
        KeyValuePipe
    ],
    templateUrl: '../../templates/building-details-page.html',
    styleUrl: '../../styles/styles.scss',
    providers: [DecimalPipe]
})
export class BuildingDetailsComponent implements OnDestroy {

    building!: BuildingDetails

    private readonly subscription: Subscription;

    constructor(private buildingService: BuildingService,
                private activateRoute: ActivatedRoute) {

        this.subscription = activateRoute.paramMap
            .pipe(switchMap((params: ParamMap) => buildingService.getBuildingDetails(1, Number(params.get("id")))))
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
        this.subscription.unsubscribe()
    }
}
