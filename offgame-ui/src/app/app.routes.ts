import {Routes} from '@angular/router';
import {BuildComponent} from "./components/building-page.component";
import {BuildingDetailsComponent} from "./components/building-details.component";
import {ResearchComponent} from "./components/research-page.component";
import {RequirementsComponent} from "./components/requirements-page.component";
import {ResearchDetailsComponent} from "./components/research-details.component";
import {SpaceYardComponent} from "./components/space-yard.component";

export const routes: Routes = [
    {path: "buildings", component: BuildComponent},
    {path: "buildings/:id", component: BuildingDetailsComponent},
    {path: "research", component: ResearchComponent},
    {path: "research/:id", component: ResearchDetailsComponent},
    {path: "requirements", component: RequirementsComponent},
    {path: "ship-yard", component: SpaceYardComponent, data: {type: "fleet"}},
    {path: "defense-yard", component: SpaceYardComponent, data: {type: "defense"}},
    {path: "**", redirectTo: "/buildings"}
];
