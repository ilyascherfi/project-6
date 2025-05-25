import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Subscription, take, tap } from 'rxjs';
import { Theme } from '../interfaces/theme.interface';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

    private pathService = 'http://localhost:8080/api/theme';

    constructor(private httpClient: HttpClient) {
      this.subscription = this.loadInitialData().pipe(take(1)).subscribe();
    }
    public subscription!: Subscription
    public themes: Theme[] = {} as Theme[];
    public themes$ = new BehaviorSubject<Theme[]>(this.themes)

    ngOnDestroy(): void {
      this.subscription.unsubscribe();
    }

    public loadInitialData() {
      return this.httpClient.get<Theme[]>(`${this.pathService}`).pipe(
        tap((value) => this.themes$.next(value)),
        catchError((error, caught) => {
          console.error("An error occured loading data");
          this.themes$.next(this.themes);
          return caught;
        })
      );
    }
    getThemes() {
      return this.themes$.asObservable();
    }
}
