import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../app.config";
import {map, Observable, Subject} from "rxjs";
import {Resources} from "../model/resource/Resources";

@Injectable({
    providedIn: 'root'
})
export class ResourceService {

    private resources: Map<number, Subject<Resources>> = new Map<number, Subject<Resources>>();
    private resources$: Map<number, Observable<Resources>> = new Map<number, Observable<Resources>>();

    constructor(private http: HttpClient) {
    }

    getPlanetResources(planetId: number): Observable<Resources> {
        return this.resources$.get(planetId)!;
    }

    updateResources(planetId: number) {
        let planetResources = this.resources.get(planetId);
        if (!planetResources) {
            planetResources = new Subject<Resources>();
            this.resources.set(planetId, planetResources);
            this.resources$.set(planetId, planetResources.asObservable());
        }

        this.http.get(`${apiUrl}/planet/${planetId}/resources`).pipe(map((data: any) => {
            return new Resources(data)
        })).subscribe({
            next: (data: Resources) => {
                planetResources?.next(data)
            }
        })
    }
}