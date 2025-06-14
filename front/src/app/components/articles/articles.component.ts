import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { SessionService } from '../../services/session.service';
import { ArticleService } from '../../services/article.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ArticlePreview } from '../../interfaces/article-preview';
import { ArticlePreviewComponent } from "./article-preview/article-preview.component";
import { MatCardModule } from '@angular/material/card';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-articles',
  imports: [
    HeaderComponent,
    ArticlePreviewComponent,
    MatCardModule,
    MatIconModule,
  ],
  templateUrl: './articles.component.html',
  styleUrl: './articles.component.scss'
})
export class ArticlesComponent implements OnInit, OnDestroy {

  constructor(
    public sessionService: SessionService,
    public articleService: ArticleService,
    public router: Router,
  ) { }

  public onError: Boolean = false;
  public subscription!: Subscription;
  public onArrowClicked: Boolean = false;
  public articles: ArticlePreview[] = new Array();

  ngOnInit(): void {
    this.getArticles();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe()
  }

  private getArticles(): void {
    this.subscription = this.articleService.getArticlesOfCurrentUser().subscribe({
      next: (articles: ArticlePreview[]) => {
        this.articles = articles.sort((a, b) => {
          return new Date(b.date).getTime() - new Date(a.date).getTime();
        });
      },
      error: (error) => {
        this.onError = true;
        console.error('Error fetching articles:', error);
      }
    });
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
