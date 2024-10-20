import {Resources} from "./resource/Resources";
import {Resource} from "./resource/Resource";
import {Requirement} from "./Requirement";

export class BuildingDetails {

    description!: string;
    imageName!: string;
    name!: string;
    currentLevel!: number;
    productionByLevel!:Map<number, Resources>;
    differenceByLevel!:Map<number, Resources>;
    requirements: Requirement[];

    constructor(data: any) {
        Object.assign(this, data);

        this.productionByLevel = new Map<number, Resources>();
        for (let [key, value] of Object.entries(data.productionByLevel)) {
            this.productionByLevel.set(Number(key), new Resources(value))
        }

        this.differenceByLevel = new Map<number, Resources>();
        for (let [key, value] of Object.entries(data.differenceByLevel)) {
            this.differenceByLevel.set(Number(key), new Resources(value))
        }

        this.requirements = data.requirements.map((object: any) => new Requirement(object))
    }

    isResourceAffected(getter: (resource: Resources) => Resource):number {
        let result = 0;

        for (let value of this.productionByLevel.values()) {
            let res = getter(value);
            let x = res.productionPerHour != 0 ? res.productionPerHour : res.amount;

            result = Math.sign(x);
        }

        return result;
    }
}