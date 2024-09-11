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
}