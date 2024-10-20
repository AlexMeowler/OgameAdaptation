import {ResourceContext, TYPE_CRYSTAL} from "./ResourceContext";
import {Resources} from "./Resources";
import {Resource} from "./Resource";

export class Crystal extends ResourceContext {

    constructor(data: Resources, widthStyle: string) {
        super(data, TYPE_CRYSTAL, widthStyle);
        this.resource = new Resource(data.crystal, data.globalEffectiveness);
        this.tooltip = 'Кристалл';
    }
}