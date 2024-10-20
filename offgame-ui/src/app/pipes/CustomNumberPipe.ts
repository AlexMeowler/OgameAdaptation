import {Pipe, PipeTransform} from "@angular/core";
import {DecimalPipe} from "@angular/common";

@Pipe({
    name: "customNumber",
    standalone: true
})
export class CustomNumberPipe implements PipeTransform {

    constructor(private decimalPipe:DecimalPipe) {
    }

    transform(value: number, signPositive?:boolean): string | null {
        let rounded = Math.round(value);
        let signStr = signPositive && rounded > 0 ? '+' : '';
        return signStr + this.decimalPipe.transform(rounded, undefined, "de-DE");
    }

}