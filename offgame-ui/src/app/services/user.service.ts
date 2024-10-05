import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../app.config";
import {BehaviorSubject, map, Observable, Subject} from "rxjs";
import {User} from "../model/User";
import {PlanetItem} from "../model/PlanetItem";

@Injectable({
    providedIn: 'root'
})
export class UserService {

    private userInfo: Subject<User | undefined> = new BehaviorSubject<User | undefined>(undefined);
    private userInfo$: Observable<User | undefined> = this.userInfo.asObservable();

    private planetList: Subject<PlanetItem[]> = new BehaviorSubject<PlanetItem[]>([]);
    private planetList$: Observable<PlanetItem[]> = this.planetList.asObservable();

    constructor(private http: HttpClient) {
        this.updatePlanetList();
        this.updateUserInfo();
    }

    getUserInfo(): Observable<User | undefined> {
        return this.userInfo$;
    }

    updateUserInfo() {
        this.http.get(`${apiUrl}/user/details`).pipe(map((data: any) => {
            return new User(data)
        })).subscribe({
            next: (data: User) => {
                this.userInfo.next(data);
            }
        })
    }

    setActivePlanet(planetId: number) {
        this.http.post(`${apiUrl}/user/activePlanet`, planetId).pipe(map((data: any) => {
            return new User(data)
        })).subscribe({
            next: (data: User) => {
                this.userInfo.next(data);
            }
        })
    }

    getPlanetList(): Observable<PlanetItem[]> {
        return this.planetList$;
    }

    updatePlanetList() {
        this.http.get(`${apiUrl}/planet/list`).pipe(map((data: any) => {
            return data.map((object: any) => new PlanetItem(object))
        })).subscribe({
            next: (data: PlanetItem[]) => {
                this.planetList.next(data);
            }
        })
    }
}