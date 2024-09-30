import {Routes} from '@angular/router';
import {BuildComponent} from "./components/building-page.component";
import {BuildingDetailsComponent} from "./components/building-details.component";

export const routes: Routes = [
    {path: "", component: BuildComponent},
    {path: `building/:id`, component: BuildingDetailsComponent},
    {path: "**", redirectTo: "/"}
];
