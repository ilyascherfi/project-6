import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { RegisterRequest } from '../components/auth/interfaces/registerRequest.interface';
import { Observable } from 'rxjs';
import { LoginRequest } from '../components/auth/interfaces/loginRequest.interface';
import { AuthSuccess } from '../components/auth/interfaces/authSuccess.interface';
import { UserInformation } from '../interfaces/user-information';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private pathService = 'http://localhost:8080/api/auth';

  constructor(private httpClient: HttpClient) { }

  public register(registerRequest: RegisterRequest): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/register`, registerRequest);
  }

  public login(loginRequest: LoginRequest): Observable<AuthSuccess> {
    return this.httpClient.post<AuthSuccess>(`${this.pathService}/login`, loginRequest);
  }

  public authenticate(token: string): Observable<UserInformation> {
    const httpHeaders: HttpHeaders = new HttpHeaders({
        Authorization: `Bearer ${token}`
    });
    return this.httpClient.get<UserInformation>(`${this.pathService}/me`, { headers: httpHeaders });
  }
}
