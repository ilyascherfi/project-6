import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { ArticleService } from '../../../services/article.service';
import { Theme } from '../../../interfaces/theme.interface';
import { ThemeService } from '../../../services/theme.service';
@Component({
  selector: 'app-add-article',
  imports: [],
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


}
