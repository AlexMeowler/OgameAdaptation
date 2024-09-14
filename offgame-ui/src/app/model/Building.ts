export class Building {

    costCrystal!: bigint;
    costDeuterium!: bigint;
    costEnergy!: bigint;
    costMetal!: bigint;
    fullDescription!: string;
    id!: bigint;
    imageName!: string;
    name!: string;
    shortDescription!: string;

    constructor(data: Building) {
        Object.assign(this, data);
    }
}