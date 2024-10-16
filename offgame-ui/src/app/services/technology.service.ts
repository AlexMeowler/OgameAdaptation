import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../app.config";
import {map, Observable} from "rxjs";
import {TechnologyDetails} from "../model/TechnologyDetails";

@Injectable({
    providedIn: 'root'
})
export class TechnologyService {

    constructor(private http: HttpClient) {
    }

    getTechnologyDetails(planetId:number, technologyId: number):Observable<TechnologyDetails> {
        return this.http.get(`${apiUrl}/planet/${planetId}/technologies/${technologyId}/details`).pipe(map((data: any) => {
            return new TechnologyDetails(data)
        }))
    }
}