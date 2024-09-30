import {Resources} from "./resource/Resources";
import {Resource} from "./resource/Resource";

export class BuildingDetails {

    description!: string;
    imageName!: string;
    name!: string;
    productionByLevel!:Map<number, Resources>;
    differenceByLevel!:Map<number, Resources>;

    constructor(data: BuildingDetails) {
        Object.assign(this, data);

        this.productionByLevel = new Map<number, Resources>();
        for (let [key, value] of Object.entries(data.productionByLevel)) {
            this.productionByLevel.set(Number(key), new Resources(value))
        }

        this.differenceByLevel = new Map<number, Resources>();
        for (let [key, value] of Object.entries(data.differenceByLevel)) {
            this.differenceByLevel.set(Number(key), new Resources(value))
        }
    }

    isResourceAffected(getter: (resource: Resources) => Resource):boolean {
        let result = false;

        for (let value of this.productionByLevel.values()) {
            let res = getter(value);
            result ||= res.productionPerHour != 0 || res.amount != 0;
        }

        return result;
    }
}