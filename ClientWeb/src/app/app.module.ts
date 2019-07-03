import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HomeComponent} from './home/home.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatCheckboxModule} from '@angular/material';
import {BsDropdownModule, ModalModule, TooltipModule} from 'ngx-bootstrap';
import {ShowsComponent} from './shows/shows.component';
import {ShowsService} from './shows.service';
import {HttpClientModule} from '@angular/common/http';
import {ManagerComponent} from './manager/manager.component';
import {AuthService} from './auth.service';
import { ManagerSpectacoleComponent } from './manager/manager-spectacole/manager-spectacole.component';
import { SpectacolCurentComponent } from './manager/spectacol-curent/spectacol-curent.component';
import { ClientComponent } from './client/client.component';
import { ClientRezervariComponent } from './client/client-rezervari/client-rezervari.component';
import { RezervariComponent } from './client/rezervari/rezervari.component';
import {RezervareService} from './rezervare.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ShowsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    HttpClientModule,
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot()
  ],
  providers: [ShowsService, AuthService, RezervareService],
  bootstrap: [AppComponent],
  exports: [MatButtonModule, MatCheckboxModule],
})
export class AppModule { }
