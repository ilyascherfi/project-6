import { Routes } from '@angular/router';
import { ArticleComponent } from './components/article/article.component';

export const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () => import('./components/auth/auth-routing.routes').then(m => m.routes)
  },
  { title: 'article', path: 'article', component: ArticleComponent }
];
