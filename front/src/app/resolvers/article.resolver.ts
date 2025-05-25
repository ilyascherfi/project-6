import { ActivatedRouteSnapshot, ResolveFn } from "@angular/router";
import { inject } from "@angular/core";
import { Article } from "../interfaces/article.interface";
import { ArticleService } from "../services/article.service";

export const articleResolver: ResolveFn<Article> = (
  route: ActivatedRouteSnapshot,
) => {
  return inject(ArticleService).getArticle(+route.paramMap.get('id')!);
};
