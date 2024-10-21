export class PlanetItem {

    id!:number
    name!:string
    imageName!:string
    usedFields!:number
    totalFields!:number
    leftFields: number

    constructor(data: PlanetItem) {
        Object.assign(this, data);

        this.leftFields = this.totalFields - this.usedFields;
    }
}