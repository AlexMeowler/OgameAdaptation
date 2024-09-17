import {Resource} from "./Resource";

export class Resources {
    metal!: Resource
    crystal!: Resource
    deuterium!: Resource
    energy!: Resource

    constructor(data: Resources) {
        Object.assign(this, data);
    }
}