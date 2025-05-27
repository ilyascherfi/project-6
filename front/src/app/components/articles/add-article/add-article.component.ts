import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { ArticleService } from '../../../services/article.service';
import { Theme } from '../../../interfaces/theme.class';
import { ThemeService } from '../../../services/theme.service';
import { NgFor } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { PostArticle } from '../../../interfaces/post-article';
@Component({
  selector: 'app-add-article',
  imports: [
   MatCardModule,
    MatIconModule,
    ReactiveFormsModule,
    RouterLink,
    NgFor
  ],
  templateUrl: './add-article.component.html',
  styleUrl: './add-article.component.scss'
})
export class AddArticleComponent implements OnInit, OnDestroy {

  private themeService: ThemeService = inject(ThemeService)
  constructor(private fb: FormBuilder,
    private router: Router,
    private articleService: ArticleService) { }

  public onError: Boolean = false;
  public subscription!: Subscription;
  public form!: FormGroup;
  public themes$: Observable<Theme[]> = this.themeService.themes$;
  public themes: Theme[] = this.themeService.themes;

  ngOnInit(): void {
    this.subscription = this.themes$.subscribe(themes => {
      this.themes = themes
    })

    this.form = this.fb.group({
      theme: [
        "",
        [Validators.required,]
      ],
      title: [
        "",
        [Validators.required,]
      ],
      content: [
        "",
        [Validators.required,]
      ]
    });
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }

  public onSubmit() {
    let request: PostArticle = this.form.value as PostArticle;
    this.subscription.unsubscribe();
    this.subscription = this.articleService.postArticle(request).subscribe({
      next: (_: any) => {
        this.router.navigate(['/articles'])
      },
      error: (error: any) => {
        this.onError = true;
        console.log(error);
      }
    });
  }


}
