export class PlanetItem {

    id!:number
    name!:string
    imageName!:string

    constructor(data: PlanetItem) {
        Object.assign(this, data);
    }
}