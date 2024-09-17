import {Building} from "./Building";
import {Resources} from "./resource/Resources";

export class BuildingInstance {

    building!: Building
    level!: number
    buildingTime!: number
    buildingCost!: Resources

    constructor(data: BuildingInstance) {
        Object.assign(this, data);
    }
}