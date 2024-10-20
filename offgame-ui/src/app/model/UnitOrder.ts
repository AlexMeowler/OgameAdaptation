import {BuildingOrder} from "./BuildingOrder";

export class UnitOrder {

    id!:number
    currentUnitEndTime?: string;
    currentUnitEndTimeDate?: Date;
    name!: string;
    singleUnitDuration!:number;
    amountLeft!: number;

    timeLeft: number = 0;

    constructor(data: BuildingOrder) {
        Object.assign(this, data);

        if(this.currentUnitEndTime) {
            this.currentUnitEndTimeDate = new Date(this.currentUnitEndTime)
            this.timeLeft = Math.round((this.currentUnitEndTimeDate.getTime() - new Date().getTime()) / 1000)
        }
    }
}