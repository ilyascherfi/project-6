import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class UserService {

    private pathService = 'http://localhost:8080/api/user';

    constructor(private httpClient: HttpClient) { }

    public themeSubscribe(themeId: number): any {
        return this.httpClient.put(`${this.pathService}/subscribe/${themeId}`, null, { responseType: 'text' });
    }

    public themeUnsubscribe(themeId: number): any {
        return this.httpClient.put(`${this.pathService}/unSubscribe/${themeId}`, null, { responseType: 'text' });
    }
}
