import {Building} from "./Building";

export class Technology {

    id!: number;
    imageName!: string;
    name!: string;
    shortDescription!: string;

    constructor(data: Building) {
        Object.assign(this, data);
    }
}