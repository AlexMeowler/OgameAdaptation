import {Component, OnInit} from '@angular/core';
import {PlanetService} from "../services/planet.service";
import {DecimalPipe, NgForOf, NgIf, NgTemplateOutlet} from "@angular/common";
import {Resources} from "../model/Resources";
import {BigIntPipe} from "../pipes/BigIntPipe";

@Component({
    selector: 'resource-header',
    standalone: true,
    imports: [
        NgForOf,
        NgIf,
        DecimalPipe,
        NgTemplateOutlet,
        BigIntPipe
    ],
    templateUrl: '../../templates/resource-header.html',
    styleUrl: '../../styles/styles.scss',
    providers: [PlanetService]
})
export class ResourceHeaderComponent implements OnInit {

    resources!: Resources

    //todo separate model and other 4 types
    //then tooltips
    //and calculations per second
    contextMetal = {
        widthStyle: "w_80",
        amount: BigInt(0),
        resType: "metal"
    }

    constructor(private planetService: PlanetService) {
    }

    ngOnInit() {
        this.planetService.getPlanetResources(BigInt(1)).subscribe({next: (data: Resources) => {
                this.resources = data;
                this.contextMetal.amount = this.resources.metal

                setTimeout(()=>{
                    this.resources.metal = BigInt(1000000)
                }, 1000);
            }})
    }
}
