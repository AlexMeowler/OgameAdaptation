import {ResourceContext, TYPE_ENERGY} from "./ResourceContext";
import {Resources} from "./Resources";
import {Resource} from "./Resource";

export class Energy extends ResourceContext {

    constructor(data: Resources, widthStyle:string) {
        super(data, TYPE_ENERGY, widthStyle);
        this.resource = new Resource(data.energy, this.getColor);
        this.tooltip = 'Энергия';
    }

    getColor(resource: Resource): string {
        return resource.amount >= 0 ? 'white' : 'red';
    }
}