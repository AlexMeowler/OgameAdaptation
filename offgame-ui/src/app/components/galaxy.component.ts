import {Component, OnDestroy} from '@angular/core';
import {DecimalPipe, NgForOf, NgIf, NgOptimizedImage, NgTemplateOutlet} from "@angular/common";
import {ActivatedRoute, NavigationExtras, Params, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {User} from "../model/User";
import {UserService} from "../services/user.service";
import {GalaxyService} from "../services/galaxy.service";
import {PlanetItem} from "../model/PlanetItem";
import {FormsModule} from "@angular/forms";

@Component({
    selector: 'galaxy-view',
    standalone: true,
    imports: [
        FormsModule,
        NgOptimizedImage,
        NgIf,
        NgTemplateOutlet,
        NgForOf
    ],
    templateUrl: '../../templates/galaxy.html',
    styleUrl: '../../styles/styles.scss',
    providers: [DecimalPipe]
})
export class GalaxyComponent implements OnDestroy {

    galaxy!: number
    galaxyInput!: number

    system!: number
    systemInput!: number

    userSubscription: Subscription
    user!: User

    planetMap!: Map<number, PlanetItem>

    constructor(private galaxyService: GalaxyService,
                private userService: UserService,
                private activateRoute: ActivatedRoute,
                private router: Router) {

        this.userSubscription = this.userService.getUserInfo().subscribe({
            next: (data?: User) => {
                if (data) {
                    this.user = data
                }
            }
        })

        this.activateRoute.queryParams.subscribe({
            next: (params: Params) => {
                this.galaxy = params['g']
                this.system = params['s']
                this.galaxyInput = this.galaxy
                this.systemInput = this.system
                this.getPlanets()
            }
        })
    }

    private getPlanets() {
        this.galaxyService.getSystemPlanets(this.galaxy, this.system)
            .subscribe({
                next: (data: PlanetItem[]) => {
                    this.planetMap = new Map<number, PlanetItem>
                    data.forEach(pi => this.planetMap.set(pi.position, pi))
                }
            })
    }

    navigateTo(galaxy: number, system: number) {
        let queryParams: NavigationExtras = {
            queryParams: {'g': galaxy, 's': system}
        };

        this.router.navigate(['/galaxy'], queryParams)
    }

    ngOnDestroy(): void {
        this.userSubscription.unsubscribe()
    }

    protected readonly Number = Number;
}
