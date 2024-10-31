export class PlanetItem {

    id!:number
    name!:string
    imageName!:string
    usedFields!:number
    totalFields!:number
    leftFields: number
    minTemperature!:number
    maxTemperature!:number
    galaxy!: number
    system!: number
    position!: number

    constructor(data: PlanetItem) {
        Object.assign(this, data);

        this.leftFields = this.totalFields - this.usedFields;
    }

    getFullName() {
        return `${this.name} [${this.galaxy}:${this.system}:${this.position}]`
    }
}