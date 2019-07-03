import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {ShowsComponent} from './shows/shows.component';
import {ManagerComponent} from './manager/manager.component';

const routes: Routes = [
  {path: '', redirectTo: 'theater', pathMatch: 'full'},
  {path: 'theater', component: HomeComponent},
  {path: 'shows', component: ShowsComponent},
  {path: 'auth', loadChildren: './auth/auth.module#AuthModule'},
  {path: 'manager', loadChildren: './manager/manager.module#ManagerModule'},
  {path: 'client', loadChildren: './client/client.module#ClientModule'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
