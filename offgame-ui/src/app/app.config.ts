import {ApplicationConfig, provideZoneChangeDetection} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi} from "@angular/common/http";
import {WithCredentialsInterceptor} from "./services/credentials.interceptor";

const address = 'localhost'
const port = 8080

export const apiUrl = `http://${address}:${port}/api`

const httpInterceptor = {
  provide: HTTP_INTERCEPTORS,
  useClass: WithCredentialsInterceptor,
  multi: true
}

export const appConfig: ApplicationConfig = {
  providers: [
    httpInterceptor,
    provideHttpClient(withInterceptorsFromDi()),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes)
  ]
};