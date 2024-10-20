import {bootstrapApplication} from '@angular/platform-browser';
import {appConfig} from './app/app.config';
import {PageWithResourcesComponent} from "./app/components/page-with-resources.component";

bootstrapApplication(PageWithResourcesComponent, appConfig)
    .catch((err) => console.error(err));
