import {Component} from '@angular/core';
import {RouterOutlet} from "@angular/router";

@Component({
    selector: 'offgame-app',
    standalone: true,
    imports: [
        RouterOutlet
    ],
    template: '<router-outlet/>',
    styleUrl: '../../styles/styles.scss',
    providers: []
})
export class ApplicationMainComponent {

    constructor() {
    }
}
