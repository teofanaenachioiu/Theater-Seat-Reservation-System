import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ShowsComponent} from './shows.component';
import {ShowsManagerComponent} from './shows-manager.component';
import {HomeManagerComponent} from './home-manager/home-manager.component';

const routes: Routes = [
  {path: '', component: ShowsComponent},
  {path: 'user/:id', redirectTo: ':id/shows', pathMatch: 'full' },
  {path: 'user/:id/shows', component: ShowsManagerComponent},
  {path: 'manager', component: HomeManagerComponent},
  {path: 'manager/shows', component: ShowsManagerComponent}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ShowsRoutingModule {
}
