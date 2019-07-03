import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ShowsComponent} from './shows.component';

const routes: Routes = [
  {path: '', component: ShowsComponent},
  // {path: 'user', loadChildren: './client-view/client-view.module#ManagerModule'},
  // {path: 'manager', component: HomeManagerComponent},
  // {path: 'manager/shows', component: ShowsManagerComponent}
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
