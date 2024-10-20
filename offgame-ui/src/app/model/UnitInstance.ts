import {Resources} from "./resource/Resources";
import {Unit} from "./Unit";
import {Requirement} from "./Requirement";

export class UnitInstance {

    unit!: Unit
    amount!: number
    buildingTime!: number
    buildingCost!: Resources
    requirements: Requirement[]

    constructor(data: any) {
        Object.assign(this, data);

        this.requirements = data.requirements.map((object: any) => new Requirement(object))
    }
}