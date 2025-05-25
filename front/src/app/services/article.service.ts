import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { HttpClient } from "@angular/common/http";
import { Article } from '../interfaces/article.interface';
import { PostArticle } from '../interfaces/post-article';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private pathService = 'http://localhost:8080/api/article';

  constructor(private httpClient: HttpClient) { }
  public getArticlesByThemes(themeIds: number[]): Observable<[]> {
    return this.httpClient.post<[]>(`${this.pathService}/get`, themeIds);
  }

  public getArticle(articleId: number): Observable<Article> {
    return this.httpClient.get<Article>(`${this.pathService}/${articleId}`);
  }

  public postArticle(article: PostArticle): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}`, article);
  }
}
