import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../app.config";
import {map, Observable} from "rxjs";
import {Building} from "../model/Building";

@Injectable()
export class BuildingService {
    constructor(private http: HttpClient) {
    }

    getBuildingList(): Observable<Building[]> {
        return this.http.get(`${apiUrl}/building/list`).pipe(map((data: any) => {
            return data.map((object: any) => new Building(object))
        }))
    }

    getPlanetBuildings(planetId: bigint): Observable<Map<bigint, bigint>> {
        return this.http.get(`${apiUrl}/planet/${planetId}/buildings`).pipe(map((data: any) => {
            let map = new Map<bigint, bigint>();
            data.map((object: any) => map.set(object.building_id, object.level))
            return map;
        }))
    }
}