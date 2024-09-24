import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../app.config";

@Injectable()
export class OrderService {
    constructor(private http: HttpClient) {
    }

    deleteOrder(orderId: number) {
        return this.http.delete(`${apiUrl}/order/${orderId}`)
    }
}