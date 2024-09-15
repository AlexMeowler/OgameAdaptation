export class Building {

    costCrystal!: number;
    costDeuterium!: number;
    costEnergy!: number;
    costMetal!: number;
    fullDescription!: string;
    id!: number;
    imageName!: string;
    name!: string;
    shortDescription!: string;

    constructor(data: Building) {
        Object.assign(this, data);
    }
}