import { Routes } from '@angular/router';
import { ArticlesComponent } from "./components/articles/articles.component";
import { AuthGuard } from './guards/auth.guard';
import { UnauthGuard } from './guards/unauth.guard';
import { AddArticleComponent } from './components/articles/add-article/add-article.component';
import { ArticleComponent } from './components/articles/article/article.component';
import { articleResolver } from './resolvers/article.resolver';
import { ThemesComponent } from './components/themes/themes.component';
import { ProfileComponent } from './components/profile/profile.component';

export const routes: Routes = [
  {
    path: 'auth',
    canActivate: [UnauthGuard],
    loadChildren: () => import('./components/auth/auth-routing.routes').then(m => m.routes)
  },
  { path: '', redirectTo: '/articles', pathMatch: 'full' },
  {
    path: 'articles',
    canActivate: [AuthGuard],
    component: ArticlesComponent
  },
  {
    path: 'articles/add',
    canActivate: [AuthGuard],
    component: AddArticleComponent
  },
  {
    path: 'articles/:id',
    canActivate: [AuthGuard],
    component: ArticleComponent,
    resolve: {
      articleDetails: articleResolver
    }
  },
  {
    path: 'themes',
    canActivate: [AuthGuard],
    component: ThemesComponent
  },
  {
    path: 'profile',
    canActivate: [AuthGuard],
    component: ProfileComponent
  },

];
