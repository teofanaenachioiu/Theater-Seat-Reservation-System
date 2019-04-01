import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {ShowsComponent} from './shows/shows.component';
import {ReservationComponent} from './reservation/reservation.component';
import {StartDayComponent} from './start-day/start-day.component';
import {MyListComponent} from './my-list/my-list.component';
import {ShowManagerComponent} from './show-manager/show-manager.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'join', component: LoginComponent},
  { path: 'shows', component: ShowsComponent},
  { path: 'reservation', component: ReservationComponent},
  { path: 'startDay', component: StartDayComponent},
  { path: 'myList', component: MyListComponent},
  { path: 'showManager', component: ShowManagerComponent}

];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
