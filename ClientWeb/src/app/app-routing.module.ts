import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';

const routes: Routes = [
  {path: '', redirectTo: 'theater', pathMatch: 'full'},
  {path: 'theater', component: HomeComponent},
  {path: 'theater/auth', loadChildren: './auth/auth.module#AuthModule'},
  {path: 'theater/shows', loadChildren: './shows/shows.module#ShowsModule'},
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
