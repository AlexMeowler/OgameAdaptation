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
    //TODO продумать иерархию, чтобы использовать ресурсы из верхней части экрана
    // внешний компонент держит ресурсы и внутренний, а внутренний содержит роутер??
    constructor() {
    }
}
