import {Component, OnInit} from '@angular/core';
import {PlanetService} from "../services/planet.service";
import {DecimalPipe, NgForOf, NgIf, NgOptimizedImage, NgTemplateOutlet} from "@angular/common";
import {TooltipDirective} from "./tooltip/tooltip.directive";
import {BuildingInstance} from "../model/BuildingInstance";
import {CustomNumberPipe} from "../pipes/CustomNumberPipe";
import {DurationPipe} from "../pipes/DurationPipe";
import {ENERGY_DIFF_NEGATIVE_TOOLTIP, ENERGY_DIFF_POSITIVE_TOOLTIP} from "../app.config";

@Component({
    selector: 'build-page',
    standalone: true,
    imports: [
        NgForOf,
        NgIf,
        TooltipDirective,
        NgOptimizedImage,
        CustomNumberPipe,
        NgTemplateOutlet,
        DurationPipe
    ],
    templateUrl: '../../templates/build-page.html',
    styleUrl: '../../styles/styles.scss',
    providers: [PlanetService, DecimalPipe]
})
export class BuildComponent implements OnInit {

    buildingInstances: BuildingInstance[] = []

    constructor(private planetService: PlanetService) {
    }

    ngOnInit() {
        this.planetService.getPlanetBuildings(1).subscribe({next: (data: BuildingInstance[]) => this.buildingInstances = data})
    }

    protected readonly ENERGY_DIFF_POSITIVE_TOOLTIP = ENERGY_DIFF_POSITIVE_TOOLTIP;
    protected readonly ENERGY_DIFF_NEGATIVE_TOOLTIP = ENERGY_DIFF_NEGATIVE_TOOLTIP;
}
