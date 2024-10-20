import {SafeHtml} from "@angular/platform-browser";

export class Unit {

    id!: number;
    imageName!: string;
    name!: string;
    shortDescription!: string;
    shortDescriptionHtml?: SafeHtml;

    constructor(data: any) {
        Object.assign(this, data);
    }
}