import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../app.config";
import {map, Observable} from "rxjs";
import {Building} from "../model/Building";
import {Resources} from "../model/resource/Resources";

@Injectable()
export class PlanetService {
    constructor(private http: HttpClient) {
    }

    getBuildingList(): Observable<Building[]> {
        return this.http.get(`${apiUrl}/building/list`).pipe(map((data: any) => {
            return data.map((object: any) => new Building(object))
        }))
    }

    getPlanetBuildings(planetId: number): Observable<Map<number, number>> {
        return this.http.get(`${apiUrl}/planet/${planetId}/buildings`).pipe(map((data: any) => {
            let map = new Map<number, number>();
            data.map((object: any) => map.set(object.building_id, object.level))
            return map;
        }))
    }

    getPlanetResources(planetId: number): Observable<Resources> {
        return this.http.get(`${apiUrl}/planet/${planetId}/resources`).pipe(map((data: any) => {
            return new Resources(data)
        }))
    }
}