import {Component, OnInit} from '@angular/core';
import {PlanetService} from "../services/planet.service";
import {DecimalPipe, NgForOf, NgIf, NgTemplateOutlet, registerLocaleData} from "@angular/common";
import {Resources} from "../model/resource/Resources";
import {CustomNumberPipe} from "../pipes/CustomNumberPipe";
import {Metal} from "../model/resource/Metal";
import {Crystal} from "../model/resource/Crystal";
import {Deuterium} from "../model/resource/Deuterium";
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';
import {TooltipDirective} from "./tooltip/tooltip.directive";

@Component({
    selector: 'resource-header',
    standalone: true,
    imports: [
        NgForOf,
        NgIf,
        DecimalPipe,
        NgTemplateOutlet,
        CustomNumberPipe,
        TooltipDirective
    ],
    templateUrl: '../../templates/resource-header.html',
    styleUrl: '../../styles/styles.scss',
    providers: [PlanetService, DecimalPipe]
})
export class ResourceHeaderComponent implements OnInit {

    //todo other types
    //and then tooltips
    //and limits with highlighting
    contextMetal!: Metal
    contextCrystal!: Crystal
    contextDeuterium!: Deuterium

    constructor(private planetService: PlanetService) {
        registerLocaleData(localeDe, "de-DE", localeDeExtra);
    }

    ngOnInit() {
        this.planetService.getPlanetResources(1).subscribe({next: (data: Resources) => {
                this.contextMetal = new Metal(data, "w_80");
                this.contextCrystal = new Crystal(data, "w_80");
                this.contextDeuterium = new Deuterium(data, "w_80");
            }})
    }
}
