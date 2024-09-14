import {Component, OnInit} from '@angular/core';
import {PlanetService} from "../services/planet.service";
import {Building} from "../model/Building";
import {NgForOf, NgIf} from "@angular/common";
import {TooltipDirective} from "./tooltip/tooltip.directive";

@Component({
    selector: 'build-page',
    standalone: true,
    imports: [
        NgForOf,
        NgIf,
        TooltipDirective
    ],
    templateUrl: '../../templates/build-page.html',
    styleUrl: '../../styles/styles.scss',
    providers: [PlanetService]
})
export class BuildComponent implements OnInit {

    buildingList: Building[] = []
    buildingInstances!: Map<bigint, bigint>

    constructor(private planetService: PlanetService) {
    }

    ngOnInit() {
        this.planetService.getBuildingList().subscribe({next: (data: Building[]) => this.buildingList = data})
        this.planetService.getPlanetBuildings(BigInt(1)).subscribe({next: (data: Map<bigint, bigint>) => this.buildingInstances = data})
    }
}
