export class Resource {

    amount!: number
    productionPerSecond: number = 0
    productionPerHour!: number

    constructor(data: Resource) {
        Object.assign(this, data);
        this.productionPerSecond = this.productionPerHour / 3600;

        setInterval(this.updateAmount(), 1000);
    }

    updateAmount() {
        return () => {
            this.amount += this.productionPerSecond;
        }
    }
}