import {Component} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {baseUrl} from "./app.config";

@Component({
    selector: 'build-page',
    standalone: true,
    imports: [FormsModule],
    templateUrl: './templates/build-page.html',
    styleUrl: './styles/styles.css'
})
export class BuildComponent {
    protected name = "";
}
