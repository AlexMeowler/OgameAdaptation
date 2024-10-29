export class Resource {

    amount!: number
    productionPerHour!: number
    maxAmount!: number

    productionPerSecond: number = 0
    fillTimeLeft: number = 0
    percentFilled: number = 0

    private readonly colorFunction: ((resource: Resource) => string);

    constructor(data: Resource, globalEffectiveness: number, colorFunction?: (resource: Resource) => string) {
        Object.assign(this, data);

        this.productionPerSecond = this.productionPerHour / 3600;
        this.percentFilled = Math.min(Math.max(this.amount / this.maxAmount, 0), 1);
        this.fillTimeLeft = Math.round(Math.max(this.maxAmount - this.amount) / this.productionPerSecond)
        this.colorFunction = colorFunction ? colorFunction : this.getColorDefault;

        setInterval(this.updateAmount(), 1000);
        setInterval(this.updateTimeLeft(), 1000);
    }

    getColor(): string {
        return this.colorFunction(this);
    }

    getColorDefault(resource: Resource): string {
        return resource.amount < resource.maxAmount ? 'white' : 'red';
    }

    updateAmount() {
        return () => {
            let newAmount = this.amount + this.productionPerSecond;
            if (this.productionPerSecond > 0) {
                this.amount = Math.max(this.amount, Math.min(this.maxAmount, newAmount));
            } else if (this.productionPerSecond < 0) {
                this.amount = Math.max(0, newAmount);
            }
        }
    }

    updateTimeLeft() {
        return () => {
            this.fillTimeLeft--;
        }
    }
}