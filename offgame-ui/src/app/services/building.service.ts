import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../app.config";
import {map, Observable} from "rxjs";
import {BuildingDetails} from "../model/BuildingDetails";

@Injectable({
    providedIn: 'root'
})
export class BuildingService {

    constructor(private http: HttpClient) {
    }

    getBuildingDetails(planetId:number, buildingId: number):Observable<BuildingDetails> {
        return this.http.get(`${apiUrl}/planet/${planetId}/buildings/${buildingId}/details`).pipe(map((data: any) => {
            return new BuildingDetails(data)
        }))
    }
}