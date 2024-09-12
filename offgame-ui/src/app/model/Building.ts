export class Building {
    public id: bigint
    public name: string
    public shortDescription: string
    public fullDescription: string
    public costMetal: bigint
    public costCrystal: bigint
    public costDeuterium: bigint
    public costEnergy: bigint
    public imageName: string

    constructor(data: any) {
        this.id = data.id
        this.name = data.name
        this.shortDescription = data.shortDescription
        this.fullDescription = data.fullDescription
        this.costMetal = data.costMetal
        this.costCrystal = data.costCrystal
        this.costDeuterium = data.costDeuterium
        this.costEnergy = data.costEnergy
        this.imageName = data.imageName
    }
}