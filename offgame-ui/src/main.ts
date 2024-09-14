import {bootstrapApplication} from '@angular/platform-browser';
import {appConfig} from './app/app.config';
import {ApplicationMainComponent} from "./app/components/application.main.component";
import {ResourceHeaderComponent} from "./app/components/resource-header.component";

bootstrapApplication(ApplicationMainComponent, appConfig)
  .catch((err) => console.error(err));

bootstrapApplication(ResourceHeaderComponent, appConfig)
    .catch((err) => console.error(err));
