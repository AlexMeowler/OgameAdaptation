export class Resource {

    amount!: number
    productionPerSecond: number = 0
    productionPerHour!: number
    maxAmount!: number
    effectiveness!: number
    globalEffectiveness!: number

    private readonly colorFunction: ((resource: Resource) => string);

    constructor(data: Resource, globalEffectiveness: number, colorFunction?: (resource: Resource) => string) {
        Object.assign(this, data);

        this.globalEffectiveness = globalEffectiveness;
        this.productionPerSecond = this.productionPerHour * this.effectiveness * this.globalEffectiveness / 3600;
        this.colorFunction = colorFunction ? colorFunction : this.getColorDefault;

        setInterval(this.updateAmount(), 1000);
    }

    getColor(): string {
        return this.colorFunction(this);
    }

    getColorDefault(resource: Resource): string {
        return resource.amount < resource.maxAmount ? 'white' : 'red';
    }

    updateAmount() {
        return () => {
            if (this.amount < this.maxAmount) {
                let newAmount = this.amount + this.productionPerSecond;
                this.amount = Math.min(this.maxAmount, newAmount);
            }
        }
    }
}