import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../app.config";
import {map, Observable} from "rxjs";
import {UnitDetails} from "../model/UnitDetails";

@Injectable({
    providedIn: 'root'
})
export class UnitService {

    constructor(private http: HttpClient) {
    }

    getUnitDetails(planetId:number, unitId: number):Observable<UnitDetails> {
        return this.http.get(`${apiUrl}/planet/${planetId}/units/${unitId}/details`).pipe(map((data: any) => {
            return new UnitDetails(data)
        }))
    }
}