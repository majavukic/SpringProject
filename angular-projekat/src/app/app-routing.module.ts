import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {CreateComponent} from './create/create.component';
import {LoginComponent} from './login/login.component';
import {LoggedInGuard} from './logged-in.guard';
import {LandingComponent} from './landing/landing.component';

const routes: Routes = [
  {
    path : '',
    component : LandingComponent,
  },
  {
    path : 'namestaj',
    component : HomeComponent,
  },
  {
    path : 'namestaj/:id',
    component: HomeComponent,
  },
  {
    path : 'create',
    component: CreateComponent,
    canActivate: [ LoggedInGuard ]
  },
  {
    path: 'login',
    component: LoginComponent,
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
