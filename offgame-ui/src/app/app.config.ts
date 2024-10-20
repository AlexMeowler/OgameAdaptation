import {ApplicationConfig, provideZoneChangeDetection} from '@angular/core';
import {provideRouter, withInMemoryScrolling, withRouterConfig} from '@angular/router';

import {routes} from './app.routes';
import {provideHttpClient, withInterceptorsFromDi} from "@angular/common/http";
import {ENVIRONMENT_SPECIFIC_PROVIDERS} from "./environments/environment";

const address = 'localhost'
const port = 8080

export const apiUrl = `http://${address}:${port}/api`;

export const appConfig: ApplicationConfig = {
    providers: [
        ENVIRONMENT_SPECIFIC_PROVIDERS,
        provideHttpClient(withInterceptorsFromDi()),
        provideZoneChangeDetection({eventCoalescing: true}),
        provideRouter(routes,
            withInMemoryScrolling({
                scrollPositionRestoration: 'top',
                anchorScrolling: 'enabled',
            }),
            withRouterConfig({
                onSameUrlNavigation: "reload",
            }))
    ]
};

export const ENERGY_DIFF_POSITIVE_TOOLTIP = "Будет произведено энергии при постройке следующего уровеня";
export const ENERGY_DIFF_NEGATIVE_TOOLTIP = "Используется энергии при постройке следующего уровеня";