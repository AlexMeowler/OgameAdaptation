import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../app.config";
import {map, Observable} from "rxjs";
import {BuildingInstance} from "../model/BuildingInstance";
import {TechnologyInstance} from "../model/TechnologyInstance";
import {UnitInstance} from "../model/UnitInstance";

@Injectable({
    providedIn: 'root'
})
export class PlanetService {

    static readonly FLEET_TYPE = "fleet";

    static readonly DEFENSE_TYPE = "defense";

    constructor(private http: HttpClient) {
    }

    getPlanetBuildings(planetId: number): Observable<BuildingInstance[]> {
        return this.http.get(`${apiUrl}/planet/${planetId}/buildings`).pipe(map((data: any) => {
            return data.map((object: any) => new BuildingInstance(object))
        }))
    }

    getPlanetUnits(planetId: number, type:string): Observable<UnitInstance[]> {
        let params = {
            type: type
        }

        return this.http.get(`${apiUrl}/planet/${planetId}/units`, {params: params}).pipe(map((data: any) => {
            return data.map((object: any) => new UnitInstance(object))
        }))
    }

    getPlanetTechnologies(planetId: number): Observable<TechnologyInstance[]> {
        return this.http.get(`${apiUrl}/planet/${planetId}/technologies`).pipe(map((data: any) => {
            return data.map((object: any) => new TechnologyInstance(object))
        }))
    }
}