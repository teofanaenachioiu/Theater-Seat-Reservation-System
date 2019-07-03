import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ClientComponent} from './client.component';
import {ClientRezervariComponent} from './client-rezervari/client-rezervari.component';
import {RezervariComponent} from './rezervari/rezervari.component';

const routes: Routes = [
  {path: '', component: ClientComponent},
  {path: 'tickets', component: ClientRezervariComponent},
  {path: 'reservation', component: RezervariComponent}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ClientRoutingModule {
}
