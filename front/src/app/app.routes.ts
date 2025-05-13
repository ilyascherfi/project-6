import { Routes } from '@angular/router';

export const routes: Routes = [
  {
        path: 'auth',
        loadChildren: () => import('./components/auth/auth-routing.routes').then(m => m.routes)
    },
];
