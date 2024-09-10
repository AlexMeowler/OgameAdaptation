import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'single-producible',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './templates/app.component.html',
  styleUrl: './styles/app.component.css'
})
export class AppComponent {
  protected name = "";
}
