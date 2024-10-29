import {Resources} from "./Resources";

export class ResourceDetail {

    id!: number
    name!: string
    level!: number
    resources!: Resources

    constructor(data: any) {
        Object.assign(this, data);
    }
}