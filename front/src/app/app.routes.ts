import { Routes } from '@angular/router';
import { ArticlesComponent } from "./components/articles/articles.component";

export const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () => import('./components/auth/auth-routing.routes').then(m => m.routes)
  },
  { title: 'articles', path: 'articles', component: ArticlesComponent }
];
