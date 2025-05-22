import { Injectable, signal } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { SessionInformation } from '../interfaces/session-information.class';
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private router: Router) {}

    public isLogged = false;

    public _sessionInformation = signal<SessionInformation | undefined>(undefined);

    get sessionInformation() {
        return this._sessionInformation.asReadonly();
    }

    private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

    public $isLogged(): Observable<boolean> {
        return this.isLoggedSubject.asObservable();
    }

    public logIn(user: SessionInformation): void {
        this._sessionInformation.update(value => { return user });
        this.isLogged = true;
        this.next();
    }

    public logOut(): void {
        localStorage.removeItem('jwtToken');
        this.isLogged = false;
        this.next();
        this.router.navigate(['/auth/login'])
    }

    private next(): void {
        this.isLoggedSubject.next(this.isLogged);
    }
}
