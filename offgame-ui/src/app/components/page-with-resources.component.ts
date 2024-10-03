import {Component, OnDestroy, OnInit} from '@angular/core';
import {DecimalPipe, NgForOf, NgIf, NgOptimizedImage, NgTemplateOutlet, registerLocaleData} from "@angular/common";
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
import {RouterOutlet} from "@angular/router";
import {ResourceService} from "../services/resource.service";
import {Subscription} from "rxjs";
import {UserService} from "../services/user.service";
import {PlanetItem} from "../model/PlanetItem";
import {User} from "../model/User";

@Component({
    selector: 'offgame-app',
    standalone: true,
    imports: [
        NgForOf,
        NgIf,
        DecimalPipe,
        NgTemplateOutlet,
        CustomNumberPipe,
        TooltipDirective,
        RouterOutlet,
        NgOptimizedImage
    ],
    templateUrl: '../../templates/page-with-resources.html',
    styleUrl: '../../styles/styles.scss',
    providers: [DecimalPipe]
})
export class PageWithResourcesComponent implements OnInit, OnDestroy {

    resourcesSubscription!: Subscription
    //todo tooltips
    contextMetal!: ResourceContext
    contextCrystal!: ResourceContext
    contextDeuterium!: ResourceContext
    contextEnergy!: ResourceContext


    planetListSubscription: Subscription
    planetList: PlanetItem[] = []


    userSubscription: Subscription
    user!: User

    constructor(private resourceService: ResourceService, private userService: UserService) {
        registerLocaleData(localeDe, "de-DE", localeDeExtra);

        this.userSubscription = this.userService.getUserInfo().subscribe({
            next: (data: User) => {
                this.user = data;
                this.resourceService.updateResources(this.user.activePlanet);
                this.resourcesSubscription = this.initResourceSubscription(this.user.activePlanet);
            }
        })

        this.planetListSubscription = this.userService.getPlanetList().subscribe({
            next: (data: PlanetItem[]) => {
                this.planetList = data;
            }
        })
    }

    private initResourceSubscription(planetId: number) {
        return this.resourceService.getPlanetResources(planetId).subscribe({
            next: (data: Resources) => {
                this.contextMetal = new Metal(data, "w_80");
                this.contextCrystal = new Crystal(data, "w_80");
                this.contextDeuterium = new Deuterium(data, "w_80");
                this.contextEnergy = new Energy(data, "w_60");
            }
        })
    }

    selectPlanet(planetId: number) {
        this.userService.setActivePlanet(planetId);
    }

    ngOnInit() {

    }

    ngOnDestroy(): void {
        this.resourcesSubscription.unsubscribe()
        this.planetListSubscription.unsubscribe();
        this.userSubscription.unsubscribe();
    }


}
