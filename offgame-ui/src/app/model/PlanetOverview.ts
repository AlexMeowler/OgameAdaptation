import {TechnologyOrder} from "./TechnologyOrder";
import {BuildingOrder} from "./BuildingOrder";

export class PlanetOverview {

    serverTime!: string
    serverTimeDate: Date
    name!: string
    imageName!: string
    diameter!: number
    usedFields!: number
    totalFields!: number
    usedFieldsPercent: number
    activeTechnologyOrder?: TechnologyOrder
    activeBuildingOrder?: BuildingOrder
    spaceYardTotalTimeLeft?: number
    minTemperature!: number
    maxTemperature!: number
    galaxy!: number
    system!: number
    position!: number

    constructor(data: any) {
        Object.assign(this, data);

        this.usedFieldsPercent = this.usedFields / this.totalFields
        this.serverTimeDate = new Date(this.serverTime)
        if (data.activeTechnologyOrder) {
             this.activeTechnologyOrder = new TechnologyOrder(data.activeTechnologyOrder)
        }
        if (data.activeBuildingOrder) {
            this.activeBuildingOrder = new BuildingOrder(data.activeBuildingOrder)
        }
    }

    updateServerTime() {
        this.serverTimeDate = new Date(this.serverTimeDate.getTime() + 1000)
    }

    getCoordinates() {
        return `[${this.galaxy}:${this.system}:${this.position}]`
    }
}