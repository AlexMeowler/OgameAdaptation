import {Routes} from '@angular/router';
import {BuildComponent} from "./components/building-page.component";
import {BuildingDetailsComponent} from "./components/building-details.component";
import {ResearchComponent} from "./components/research-page.component";

export const routes: Routes = [
    {path: "buildings", component: BuildComponent},
    {path: "research", component: ResearchComponent},
    {path: "buildings/:id", component: BuildingDetailsComponent},
    {path: "**", redirectTo: "/buildings"}
];
