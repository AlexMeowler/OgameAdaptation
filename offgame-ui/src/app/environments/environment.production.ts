import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {AuthInterceptor} from "../interceptors/auth.interceptor";

const HTTP_AUTH_INTERCEPTOR = {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
}
export const ENVIRONMENT_SPECIFIC_PROVIDERS = [
    HTTP_AUTH_INTERCEPTOR
];