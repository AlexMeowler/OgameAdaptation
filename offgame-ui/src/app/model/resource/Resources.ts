import {Resource} from "./Resource";

export class Resources {
    metal!: Resource
    crystal!: Resource
    deuterium!: Resource
    energy!: Resource

    efficiency!: number
    globalEfficiency!: number

    constructor(data: any) {
        Object.assign(this, data);
    }

    initResourceLogic() {
        this.metal = new Resource(this.metal, this.globalEfficiency)
        this.crystal = new Resource(this.crystal, this.globalEfficiency)
        this.deuterium = new Resource(this.deuterium, this.globalEfficiency)
        this.energy = new Resource(this.energy, this.globalEfficiency)
    }
}