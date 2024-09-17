import {ResourceContext, TYPE_DEUTERIUM} from "./ResourceContext";
import {Resources} from "./Resources";
import {Resource} from "./Resource";

export class Deuterium extends ResourceContext {

    constructor(data: Resources, widthStyle:string) {
        super(data, TYPE_DEUTERIUM, widthStyle);
        this.resource = new Resource(data.deuterium);
        this.tooltip = 'Дейтерий';
    }
}