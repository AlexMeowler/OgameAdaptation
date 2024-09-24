//todo duration pipe i.e. x:number -> 30 дн. 02 ч. 47 мин. 11 сек.
//if days = 0 them dont write them down
import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
    name: "duration",
    standalone: true
})
export class DurationPipe implements PipeTransform {

    constructor() {
    }

    transform(value?: number, delimiter?: string): string {
        if (!value) {
            return ""
        }

        let valueInt = Math.round(value);
        let dividers = [24, 60, 60, 1];
        let values: number[] = Array(4).fill(0);
        let names = [' дн. ', ' ч. ', ' мин. ', ' сек. '];
        if (delimiter) {
            names = Array(4).fill(delimiter)
            names[3] = ''
        }

        let n = 4;
        let x = valueInt;
        let currentDivider = 24 * 60 * 60;
        for (let i = 0; i < n; i++) {
            values[i] = Math.floor(x / currentDivider);
            x = x % currentDivider;
            currentDivider = Math.floor(currentDivider / dividers[i]);
        }

        let result = '';
        for (let i = values[0] > 0 ? 0 : 1; i < n; i++) {
            result += String(values[i]).padStart(2, '0') + names[i];
        }
        return result;
    }

}