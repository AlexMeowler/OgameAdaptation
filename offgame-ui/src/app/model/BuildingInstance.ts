import {Building} from "./Building";
import {Resources} from "./resource/Resources";
import {Requirement} from "./Requirement";

export class BuildingInstance {

    building!: Building
    level!: number
    energyDiff!: number
    buildingTime!: number
    buildingCost!: Resources
    requirements: Requirement[]

    constructor(data: any) {
        Object.assign(this, data);

        this.requirements = data.requirements.map((object: any) => new Requirement(object))
    }
}