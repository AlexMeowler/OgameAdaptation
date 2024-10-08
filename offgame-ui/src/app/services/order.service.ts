import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../app.config";
import {map, Observable} from "rxjs";
import {BuildingOrder} from "../model/BuildingOrder";
import {TechnologyOrder} from "../model/TechnologyOrder";

@Injectable({
    providedIn: 'root'
})
export class OrderService {
    constructor(private http: HttpClient) {
    }

    getActiveBuildingOrders(planetId: number): Observable<BuildingOrder[]> {
        return this.http.get(`${apiUrl}/order/build/${planetId}/list`).pipe(map((data: any) => {
            return data.map((object: any) => new BuildingOrder(object))
        }))
    }

    createBuildOrder(buildingId: number, planetId: number) {
        return this.http.post(`${apiUrl}/order/build`, {
            buildingId: buildingId,
            planetId: planetId
        })
    }

    deleteBuildOrder(orderId: number) {
        return this.http.delete(`${apiUrl}/order/build/${orderId}`)
    }

    getTechnologyOrder(userId: number): Observable<TechnologyOrder | null> {
        return this.http.get(`${apiUrl}/order/research/${userId}/order`).pipe(map((data: any) => {
            return data === null ? null : new TechnologyOrder(data)
        }))
    }

    createTechnologyOrder(technologyId: number, planetId: number) {
        return this.http.post(`${apiUrl}/order/research`, {
            technologyId: technologyId,
            planetId: planetId
        })
    }

    deleteTechnologyOrder(orderId: number) {
        return this.http.delete(`${apiUrl}/order/research/${orderId}`)
    }
}