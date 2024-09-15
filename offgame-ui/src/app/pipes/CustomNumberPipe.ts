import {Pipe, PipeTransform} from "@angular/core";
import {DecimalPipe} from "@angular/common";

@Pipe({
    name: "customNumber",
    standalone: true
})
export class CustomNumberPipe implements PipeTransform {

    constructor(private decimalPipe:DecimalPipe) {
    }

    transform(value: number, args?: any): string | null {
        let rounded = Math.round(value);
        return this.decimalPipe.transform(rounded, undefined, "de-DE");
    }

}