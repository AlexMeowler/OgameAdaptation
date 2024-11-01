import {Component, OnDestroy} from '@angular/core';
import {PlanetService} from "../services/planet.service";
import {DecimalPipe, NgForOf, NgIf, NgTemplateOutlet} from "@angular/common";
import {CustomNumberPipe} from "../pipes/CustomNumberPipe";
import {Resources} from "../model/resource/Resources";
import {ResourceService} from "../services/resource.service";
import {map, Subscription} from "rxjs";
import {User} from "../model/User";
import {UserService} from "../services/user.service";
import {Emittable} from "./emittable";
import {DEFAULT_PRODUCTION} from "../app.config";
import {ResourcesDetails} from "../model/resource/ResourcesDetails";
import {FormsModule} from "@angular/forms";
import {DurationPipe} from "../pipes/DurationPipe";
import {TooltipDirective} from "./tooltip/tooltip.directive";
import {UtilService} from "../services/util.service";

@Component({
    selector: 'resources-page',
    standalone: true,
    imports: [
        CustomNumberPipe,
        NgTemplateOutlet,
        NgForOf,
        FormsModule,
        NgIf,
        DurationPipe,
        TooltipDirective
    ],
    templateUrl: '../../templates/resources-page.html',
    styleUrl: '../../styles/styles.scss',
    providers: [DecimalPipe]
})
export class ResourcesPageComponent extends Emittable implements OnDestroy {

    resourcesDetails!: ResourcesDetails

    resourcesSubscription!: Subscription
    resources!: Resources

    userSubscription: Subscription
    user!: User

    efficiencyInputs: Map<number, string> = new Map<number, string>()

    constructor(private planetService: PlanetService,
                private resourceService: ResourceService,
                private userService: UserService) {
        super();

        this.userSubscription = this.userService.getUserInfo().subscribe({
            next: (data?: User) => {
                if (data) {
                    this.user = data;
                    this.updateResourceDetails();
                    this.resourceService.updateResources(this.user.activePlanet);
                    this.resourcesSubscription = this.initResourceSubscription();
                }
            }
        })
    }

    private updateResourceDetails() {
        this.resourceService.getPlanetResourcesDetails(this.user.activePlanet).subscribe({
            next: (data: ResourcesDetails) => {
                this.resourcesDetails = data
                let details = this.resourcesDetails.resourceDetails;
                for (let i = 0; i < details.length; i++) {
                    if (details[i].id == null) {
                        continue;
                    }
                    this.efficiencyInputs.set(details[i].id, "" + details[i].resources.efficiency)
                }
            }
        })
    }

    private initResourceSubscription() {
        return this.resourceService.getPlanetResources(this.user.activePlanet).subscribe({
            next: (data: Resources) => {
                this.resources = data;
            }
        })
    }

    getColor(value: any) {
        switch (Math.sign(value)) {
            case 1:
                return "lime";
            case -1:
                return "red";
            case 0:
            default:
                return "white";
        }
    }

    applyNewEfficiency() {
        this.planetService.changeEfficiency(this.user.activePlanet, this.efficiencyInputs).subscribe({
            next: ignore => {
                window.location.reload()
            }
        })
    }

    ngOnDestroy(): void {
        this.resourcesSubscription.unsubscribe();
        this.userSubscription.unsubscribe();
    }

    protected readonly DEFAULT_PRODUCTION = DEFAULT_PRODUCTION;
    protected readonly Array = Array;
    protected readonly map = map;
    protected readonly UtilService = UtilService;
}
