import { Routes } from '@angular/router';
import { authenticatedGuard } from './core/guards/authenticated.guard';

export const routes: Routes = [
  {
    path:'',
    loadComponent :()=>import('./layout/layout/layout.component')
  },
  {
  path:'login',
  loadComponent :()=>import('./auth/login/login.component'),
  canActivate:[authenticatedGuard]
},
{
  path:'register',
  loadComponent :()=>import('./auth/register/register.component'),
  canActivate:[authenticatedGuard]
}
];
