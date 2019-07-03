import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ManagerComponent} from './manager.component';
import {ManagerSpectacoleComponent} from './manager-spectacole/manager-spectacole.component';
import {SpectacolCurentComponent} from './spectacol-curent/spectacol-curent.component';

const routes: Routes = [
  // {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: '', component: ManagerComponent},
  {path: 'shows', component: ManagerSpectacoleComponent},
  {path: 'today', component: SpectacolCurentComponent}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ManagerRoutingModule {
}
