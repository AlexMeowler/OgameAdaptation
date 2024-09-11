import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { BuildComponent } from './app/components/building-page.component';

bootstrapApplication(BuildComponent, appConfig)
  .catch((err) => console.error(err));
