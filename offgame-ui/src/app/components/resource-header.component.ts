import {Component, OnInit} from '@angular/core';
import {DecimalPipe, NgForOf, NgIf, NgTemplateOutlet, registerLocaleData} from "@angular/common";
import {CustomNumberPipe} from "../pipes/CustomNumberPipe";
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';
import {TooltipDirective} from "./tooltip/tooltip.directive";
import {ResourceContext} from "../model/resource/ResourceContext";
import {Resources} from "../model/resource/Resources";
import {Metal} from "../model/resource/Metal";
import {Crystal} from "../model/resource/Crystal";
import {Deuterium} from "../model/resource/Deuterium";
import {Energy} from "../model/resource/Energy";
import {PlanetService} from "../services/planet.service";

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

    //todo tooltips
    contextMetal!: ResourceContext
    contextCrystal!: ResourceContext
    contextDeuterium!: ResourceContext
    contextEnergy!: ResourceContext

    constructor(private planetService: PlanetService) {
        registerLocaleData(localeDe, "de-DE", localeDeExtra);

        this.planetService.getPlanetResources(1).subscribe({next: (data: Resources) => {
                this.contextMetal = new Metal(data, "w_80");
                this.contextCrystal = new Crystal(data, "w_80");
                this.contextDeuterium = new Deuterium(data, "w_80");
                this.contextEnergy = new Energy(data, "w_60");
            }})
    }

    ngOnInit() {

    }
}
