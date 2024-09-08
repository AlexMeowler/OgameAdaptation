import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'my-app',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  protected name = "";
}
