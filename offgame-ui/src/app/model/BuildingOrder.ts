export class BuildingOrder {

    id!:number
    endTime?: string;
    endTimeDate?: Date;
    name!: string;
    value!: number;

    timeLeft: number = 0;

    constructor(data: BuildingOrder) {
        Object.assign(this, data);

        if(this.endTime) {
            this.endTimeDate = new Date(this.endTime)
            this.timeLeft = Math.round((this.endTimeDate.getTime() - new Date().getTime()) / 1000)
        }
    }
}