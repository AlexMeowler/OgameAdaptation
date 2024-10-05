import {Resources} from "./resource/Resources";
import {Technology} from "./Technology";

export class TechnologyInstance {

    technology!: Technology
    level!: number
    researchTime!: number
    researchCost!: Resources

    constructor(data: TechnologyInstance) {
        Object.assign(this, data);
    }
}