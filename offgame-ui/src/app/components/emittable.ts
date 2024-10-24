import {Directive, EventEmitter, Output} from "@angular/core";

@Directive()
export abstract class Emittable {

    @Output() needResourceUpdate = new EventEmitter<void>();
}