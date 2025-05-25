import { Component, Input } from '@angular/core';
import { ArticlePreview } from '../../../interfaces/article-preview';

@Component({
  selector: 'app-article-preview',
  imports: [],
  templateUrl: './article-preview.component.html',
  styleUrl: './article-preview.component.scss'
})
export class ArticlePreviewComponent {
  @Input() article!: ArticlePreview
}
