export class BuildingImpl {

    constructor(data: BuildingImpl) {
        Object.assign(this, data);
        /*this.id = data.id
        this.name = data.name
        this.shortDescription = data.shortDescription
        this.fullDescription = data.fullDescription
        this.costMetal = data.costMetal
        this.costCrystal = data.costCrystal
        this.costDeuterium = data.costDeuterium
        this.costEnergy = data.costEnergy
        this.imageName = data.imageName*/
    }

    costCrystal!: bigint;
    costDeuterium!: bigint;
    costEnergy!: bigint;
    costMetal!: bigint;
    fullDescription!: string;
    id!: bigint;
    imageName!: string;
    name!: string;
    shortDescription!: string;
}