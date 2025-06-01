import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ModifyRequest } from "../interfaces/modify-request.interface";
import { ModifyNoPassword } from "../interfaces/modify-no-password.class";

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
    return this.httpClient.put(`${this.pathService}/unsubscribe/${themeId}`, null, { responseType: 'text' });
  }

  public modifyProfile(putRequest: ModifyRequest): any {
    return this.httpClient.put(`${this.pathService}/password`, putRequest, { responseType: 'text' });
  }
  public modifyProfileNoPassword(putRequest: ModifyNoPassword): any {
    return this.httpClient.put(`${this.pathService}/noPassword`, putRequest, { responseType: 'text' });
  }
}
