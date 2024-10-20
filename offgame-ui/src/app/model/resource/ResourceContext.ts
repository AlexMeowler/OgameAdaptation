import {Resources} from "./Resources";
import {Resource} from "./Resource";

export const TYPE_METAL = "metal";
export const TYPE_CRYSTAL = "crystal";
export const TYPE_DEUTERIUM = "deuterium";

export const TYPE_ENERGY = "energy";

export abstract class ResourceContext {
    widthStyle!: string
    resource!: Resource
    resType!: string
    tooltip?: string

    protected constructor(data: Resources, resType:string, widthStyle:string) {
        this.widthStyle = widthStyle;
        this.resType = resType;
    }
}