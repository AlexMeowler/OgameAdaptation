import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../app.config";
import {map, Observable} from "rxjs";
import {Resources} from "../model/resource/Resources";
import {BuildingInstance} from "../model/BuildingInstance";
import {BuildingOrder} from "../model/BuildingOrder";

@Injectable()
export class PlanetService {
    constructor(private http: HttpClient) {
    }

    getPlanetBuildings(planetId: number): Observable<BuildingInstance[]> {
        return this.http.get(`${apiUrl}/planet/${planetId}/buildings`).pipe(map((data: any) => {
            return data.map((object: any) => new BuildingInstance(object))
        }))
    }

    getPlanetResources(planetId: number): Observable<Resources> {
        return this.http.get(`${apiUrl}/planet/${planetId}/resources`).pipe(map((data: any) => {
            return new Resources(data)
        }))
    }

    getActiveOrders(planetId: number): Observable<BuildingOrder[]> {
        return this.http.get(`${apiUrl}/planet/${planetId}/orders`).pipe(map((data: any) => {
            return data.map((object: any) => new BuildingOrder(object))
        }))
    }

    createOrder(buildingId: number, planetId: number) {
        return this.http.post(`${apiUrl}/planet/build`, {
            buildingId: buildingId,
            planetId: planetId
        })
    }
}