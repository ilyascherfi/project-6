import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../../interfaces/article.interface';
import { ArtcileService } from '../../services/article.service';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-article',
  imports: [
    MatIcon
  ],
  templateUrl: './article.component.html',
  styleUrl: './article.component.scss'
})
export class ArticleComponent implements OnInit, OnDestroy{

  onError = false;
  constructor(private articleService: ArtcileService) {
  }

  ngOnInit(): void {

  }
  ngOnDestroy(): void {

  }
  public onClick(): void {

  }
}
