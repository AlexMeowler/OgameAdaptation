import {Component} from '@angular/core';
import {PlanetService} from "../services/planet.service";
import {DatePipe, DecimalPipe, NgForOf, NgIf, NgOptimizedImage, NgTemplateOutlet} from "@angular/common";
import {TooltipDirective} from "./tooltip/tooltip.directive";
import {BuildingInstance} from "../model/BuildingInstance";
import {CustomNumberPipe} from "../pipes/CustomNumberPipe";
import {DurationPipe} from "../pipes/DurationPipe";
import {ENERGY_DIFF_NEGATIVE_TOOLTIP, ENERGY_DIFF_POSITIVE_TOOLTIP} from "../app.config";
import {BuildingOrder} from "../model/BuildingOrder";
import {Resources} from "../model/resource/Resources";
import {Resource} from "../model/resource/Resource";
import {OrderService} from "../services/order.service";

@Component({
    selector: 'build-page',
    standalone: true,
    imports: [
        NgForOf,
        NgIf,
        TooltipDirective,
        NgOptimizedImage,
        CustomNumberPipe,
        NgTemplateOutlet,
        DurationPipe,
        DatePipe
    ],
    templateUrl: '../../templates/build-page.html',
    styleUrl: '../../styles/styles.scss',
    providers: [PlanetService, OrderService, DecimalPipe]
})
export class BuildComponent {

    buildingInstances: BuildingInstance[] = []
    buildingOrders: BuildingOrder[] = []
    currentBuildTimer!: number

    resources!: Resources

    constructor(private planetService: PlanetService, private orderService:OrderService) {
        this.planetService.getPlanetBuildings(1).subscribe({
            next: (data: BuildingInstance[]) => this.buildingInstances = data
        })

        this.planetService.getPlanetResources(1).subscribe({
            next: (data: Resources) => {
                this.resources = data;
            }
        })

        this.refreshOrders()
    }

    createOrder(buildingId: number) {
        this.planetService.createOrder(buildingId, 1).subscribe({
            next: ignore => this.refreshOrders()
        })
    }

    deleteOrder(orderId: number) {
        this.orderService.deleteOrder(orderId).subscribe({
            next: ignore => this.refreshOrders()
        })
    }

    canBuild(buildingInstance: BuildingInstance): boolean {
        let cost = buildingInstance.buildingCost
        let resources = this.resources;
        return this.hasResource(cost.metal, resources.metal)
            && this.hasResource(cost.crystal, resources.crystal)
            && this.hasResource(cost.deuterium, resources.deuterium)
            && this.hasResource(cost.energy, resources.energy)
    }

    hasResource(cost: Resource, resource: Resource): boolean {
        return cost.amount <= Math.max(0, resource.amount)
    }

    private refreshOrders() {
        this.planetService.getActiveOrders(1).subscribe({
            next: (data: BuildingOrder[]) => {
                clearInterval(this.currentBuildTimer)
                this.buildingOrders = data

                if (this.buildingOrders.length > 0) {
                    this.currentBuildTimer = setInterval(this.updateBuildingTimer(this.buildingOrders[0]), 1000)
                }
            }
        })
    }

    private updateBuildingTimer(order: BuildingOrder) {
        return () => {
            if (order.timeLeft <= 0) {
                clearInterval(this.currentBuildTimer)
                this.refreshOrders()
            }

            order.timeLeft--
        }
    }

    protected readonly ENERGY_DIFF_POSITIVE_TOOLTIP = ENERGY_DIFF_POSITIVE_TOOLTIP;
    protected readonly ENERGY_DIFF_NEGATIVE_TOOLTIP = ENERGY_DIFF_NEGATIVE_TOOLTIP;
}
