import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {FixedCredentialsAuthInterceptor} from "../interceptors/fixed.auth.interceptor";


const HTTP_AUTH_FIXED_CREDENTIALS_INTERCEPTOR = {
    provide: HTTP_INTERCEPTORS,
    useClass: FixedCredentialsAuthInterceptor,
    multi: true
}

export const ENVIRONMENT_SPECIFIC_PROVIDERS = [
    HTTP_AUTH_FIXED_CREDENTIALS_INTERCEPTOR
];