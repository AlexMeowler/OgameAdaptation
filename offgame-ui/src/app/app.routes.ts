import {Routes} from '@angular/router';
import {BuildComponent} from "./components/building-page.component";

export const routes: Routes = [
    {path: "", component: BuildComponent},
    {path: "**", redirectTo: "/"}
];
