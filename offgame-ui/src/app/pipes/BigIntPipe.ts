import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
    name: "bigint",
    standalone: true
})
export class BigIntPipe implements PipeTransform {

    transform(value: BigInt, args?: any): string {
        return value.toLocaleString("de-DE");
    }

}