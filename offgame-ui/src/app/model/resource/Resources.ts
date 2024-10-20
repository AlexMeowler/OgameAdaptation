import {Resource} from "./Resource";

export class Resources {
    metal!: Resource
    crystal!: Resource
    deuterium!: Resource
    energy!: Resource

    globalEffectiveness!: number

    constructor(data: any) {
        Object.assign(this, data);
    }
}