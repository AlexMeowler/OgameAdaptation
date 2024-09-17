import {Component, OnInit} from '@angular/core';
import {PlanetService} from "../services/planet.service";
import {DecimalPipe, NgForOf, NgIf, NgOptimizedImage, NgTemplateOutlet} from "@angular/common";
import {TooltipDirective} from "./tooltip/tooltip.directive";
import {BuildingInstance} from "../model/BuildingInstance";
import {CustomNumberPipe} from "../pipes/CustomNumberPipe";

@Component({
    selector: 'build-page',
    standalone: true,
    imports: [
        NgForOf,
        NgIf,
        TooltipDirective,
        NgOptimizedImage,
        CustomNumberPipe,
        NgTemplateOutlet
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
}
