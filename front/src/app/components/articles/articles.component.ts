import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-articles',
  imports: [
    MatIcon
  ],
  templateUrl: './articles.component.html',
  styleUrl: './articles.component.scss'
})
export class ArticlesComponent implements OnInit, OnDestroy {

  onError: boolean = false
  constructor() {

  }
  ngOnInit(): void {

  }

  ngOnDestroy(): void {

  }

}
