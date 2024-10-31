import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../app.config";
import {map, Observable} from "rxjs";
import {PlanetItem} from "../model/PlanetItem";

@Injectable({
    providedIn: 'root'
})
export class GalaxyService {

    constructor(private http: HttpClient) {
    }

    getSystemPlanets(galaxy: number, system: number): Observable<PlanetItem[]> {
        let params = {
            params: {
                galaxy: galaxy,
                system: system
            }
        }
        return this.http.get(`${apiUrl}/galaxy/list`, params).pipe(map((data: any) => {
            return data.map((object: any) => new PlanetItem(object))
        }))
    }
}