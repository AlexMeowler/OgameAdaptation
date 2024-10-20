export class User {

    userName!:string
    activePlanet!:number

    constructor(data: User) {
        Object.assign(this, data);
    }
}