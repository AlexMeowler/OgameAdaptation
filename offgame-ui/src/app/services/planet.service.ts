import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../app.config";
import {map, Observable} from "rxjs";
import {BuildingInstance} from "../model/BuildingInstance";
import {TechnologyInstance} from "../model/TechnologyInstance";

@Injectable({
    providedIn: 'root'
})
export class PlanetService {
    constructor(private http: HttpClient) {
    }

    getPlanetBuildings(planetId: number): Observable<BuildingInstance[]> {
        return this.http.get(`${apiUrl}/planet/${planetId}/buildings`).pipe(map((data: any) => {
            return data.map((object: any) => new BuildingInstance(object))
        }))
    }

    getPlanetTechnologies(planetId: number): Observable<TechnologyInstance[]> {
        return this.http.get(`${apiUrl}/planet/${planetId}/technologies`).pipe(map((data: any) => {
            return data.map((object: any) => new TechnologyInstance(object))
        }))
    }
}