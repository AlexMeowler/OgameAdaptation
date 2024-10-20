import {Resources} from "./resource/Resources";
import {Technology} from "./Technology";
import {Requirement} from "./Requirement";

export class TechnologyInstance {

    technology!: Technology
    level!: number
    researchTime!: number
    researchCost!: Resources
    requirements: Requirement[]

    constructor(data: any) {
        Object.assign(this, data);

        this.requirements = data.requirements.map((object: any) => new Requirement(object))
    }
}