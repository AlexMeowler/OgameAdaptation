import {Routes} from '@angular/router';
import {BuildComponent} from "./components/building-page.component";
import {BuildingDetailsComponent} from "./components/building-details.component";
import {ResearchComponent} from "./components/research-page.component";
import {RequirementsComponent} from "./components/requirements-page.component";

export const routes: Routes = [
    {path: "buildings", component: BuildComponent},
    {path: "buildings/:id", component: BuildingDetailsComponent},
    {path: "research", component: ResearchComponent},
    {path: "requirements", component: RequirementsComponent},
    {path: "**", redirectTo: "/buildings"}
];
