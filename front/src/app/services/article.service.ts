import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../interfaces/article.interface';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ArtcileService {

 private pathService = 'http://localhost:8080/api/article';

  constructor(private httpClient: HttpClient) { }

  public getArticlesByThemes(themeIds: number[]): Observable<Article[]> {
    return this.httpClient.post<Article[]>(`${this.pathService}/get`, themeIds);
  }



}
