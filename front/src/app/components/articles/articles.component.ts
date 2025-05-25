import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { SessionService } from '../../services/session.service';
import { ArticleService } from '../../services/article.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ArticlePreview } from '../../interfaces/article-preview';
import { ArticlePreviewComponent } from "./article-preview/article-preview.component";
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-articles',
  imports: [
    MatIcon,
    ArticlePreviewComponent,
    MatCardModule,
    MatIconModule,
],
  templateUrl: './articles.component.html',
  styleUrl: './articles.component.scss'
})
export class ArticlesComponent implements OnInit, OnDestroy {

  public onError: boolean = false
  public subscription: Subscription | undefined;
  public articles: ArticlePreview[] = new Array();
  public onArrowClicked: Boolean = false;
  constructor(public sessionService: SessionService,
              public articleService: ArticleService,
              public router: Router) {}

  ngOnInit(): void {
    this.getArticles();
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  private getArticles(): void {
    let themeIds: number[] = this.sessionService._sessionInformation()!.themes.map(theme =>
      theme.themeId
    )
    this.subscription = this.articleService.getArticlesByThemes(themeIds).subscribe()
  }

  public onAddArticle(): void {
    this.router.navigate(['articles/add'])
  }

  public onClick(id: number): void {
    this.router.navigateByUrl('articles/' + id)
  }

  public sort(): void {
    this.articles.reverse();
    this.onArrowClicked = !this.onArrowClicked;
  }

}
