import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './login/login.component';
import { ShowsComponent } from './shows/shows.component';
import { MenuComponent } from './menu/menu.component';
import { ReservationComponent } from './reservation/reservation.component';
import { MyListComponent } from './my-list/my-list.component';
import { StartDayComponent } from './start-day/start-day.component';
import { ShowManagerComponent } from './show-manager/show-manager.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ShowsComponent,
    MenuComponent,
    ReservationComponent,
    MyListComponent,
    StartDayComponent,
    ShowManagerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
