import {Building} from "./Building";

export class Requirement {

    id!: number
    imagePath!: string
    detailsPath!: string
    name!: string
    requiredLevel!: number
    currentLevel!:number

    constructor(data: Building) {
        Object.assign(this, data)
    }
}