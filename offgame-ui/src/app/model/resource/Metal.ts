import {ResourceContext, TYPE_METAL} from "./ResourceContext";
import {Resources} from "./Resources";
import {Resource} from "./Resource";

export class Metal extends ResourceContext {

    constructor(data: Resources, widthStyle:string) {
        super(data, TYPE_METAL, widthStyle);
        this.resource = new Resource(data.metal);
        this.tooltip = 'Металл<br>Вместимость: 82.500<br><br><small>Клик на иконку для просмотра деталей</small>';
        //this.tooltip = 'Металл';
    }
}