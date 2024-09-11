import {Component, OnInit} from '@angular/core';
import {BuildingService} from "../services/building.service";
import {Building} from "../model/Building";
import {NgForOf} from "@angular/common";

@Component({
    selector: 'build-page',
    standalone: true,
    imports: [NgForOf],
    templateUrl: '../../templates/build-page.html',
    styleUrl: '../../styles/styles.css',
    providers: [BuildingService]
})
export class BuildComponent implements OnInit {

    buildings: Building[] = []

    constructor(private buildingService: BuildingService) {
    }

    ngOnInit() {
        this.buildingService.getBuildingList().subscribe({next: (data: Building[]) => this.buildings = data})
    }

}
