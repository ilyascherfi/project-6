import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

 private pathService = 'http://localhost:8080/api/article';

  constructor(private httpClient: HttpClient) { }

  public getArticlesByThemes(themeIds: number[]): Observable<[]> {
    return this.httpClient.post<[]>(`${this.pathService}/get`, themeIds);
  }

}
