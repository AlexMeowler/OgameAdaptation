export class Resource {

    amount!: number
    productionPerSecond: number = 0
    productionPerHour!: number
    maxAmount!: number
    private readonly colorFunction: ((resource: Resource) => string);

    constructor(data: Resource, colorFunction?: (resource:Resource) => string) {
        Object.assign(this, data);
        this.productionPerSecond = this.productionPerHour / 3600;
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
            if(this.amount < this.maxAmount) {
                let newAmount = this.amount + this.productionPerSecond;
                this.amount = Math.min(this.maxAmount, newAmount);
            }
        }
    }
}