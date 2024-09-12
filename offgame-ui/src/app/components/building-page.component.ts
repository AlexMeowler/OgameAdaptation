import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {BuildingService} from "../services/building.service";
import {Building} from "../model/Building";
import {NgForOf, NgIf} from "@angular/common";
import {MatTooltipModule} from "@angular/material/tooltip";

@Component({
    selector: 'build-page',
    standalone: true,
    imports: [
        NgForOf,
        MatTooltipModule,
        NgIf
    ],
    templateUrl: '../../templates/build-page.html',
    styleUrl: '../../styles/styles.scss',
    providers: [BuildingService]
})
export class BuildComponent implements OnInit {

    buildingList: Building[] = []
    buildingInstances!: Map<bigint, bigint>
    @ViewChild('tooltip', {static: true}) tooltip : ElementRef<HTMLDivElement> | undefined;

    constructor(private buildingService: BuildingService, private renderer: Renderer2) {
    }

    ngOnInit() {
        this.buildingService.getBuildingList().subscribe({next: (data: Building[]) => this.buildingList = data})
        this.buildingService.getPlanetBuildings(BigInt(1)).subscribe({next: (data: Map<bigint, bigint>) => this.buildingInstances = data})
    }

    showTooltip() {
        this.renderer.setStyle(this.tooltip?.nativeElement, 'visibility', 'visible');
    }

    moveTooltip(event: MouseEvent): void {
        console.log({x: event.clientX, y: event.clientY});
        let offset = 10;
        let x = event.clientX + offset;
        let y = event.clientY + offset;
        this.renderer.setStyle(this.tooltip?.nativeElement, 'left', x + 'px');
        this.renderer.setStyle(this.tooltip?.nativeElement, 'top', y + 'px');
    }

    hideTooltip(): void {
        this.renderer.setStyle(this.tooltip?.nativeElement, 'visibility', 'hidden');
    }

    protected readonly BigInt = BigInt;
}
