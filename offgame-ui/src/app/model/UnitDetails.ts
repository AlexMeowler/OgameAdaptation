import {Requirement} from "./Requirement";

export class UnitDetails {

    id!: number
    description!: string
    imageName!: string
    name!: string
    hull!: number
    shields!: number
    attack!: number
    capacity!: number
    engineType!: string
    unitType!: string
    speed!: number
    fuelConsumption!: number
    requirements: Requirement[]

    constructor(data: any) {
        Object.assign(this, data);

        this.requirements = data.requirements.map((object: any) => new Requirement(object))
    }

    getEngineType(): string {
        switch (this.engineType) {
            case "reactive":
                return "Реактивный"
            case "impulse":
                return "Импульсный"
            case "hyperspace":
                return "Гиперпространственный"
            default:
                return "только маневровые"
        }
    }
}