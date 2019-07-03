import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ClientRoutingModule} from './client-routing.module';
import {ClientComponent} from './client.component';
import {ClientRezervariComponent} from './client-rezervari/client-rezervari.component';
import {RezervariComponent} from './rezervari/rezervari.component';

@NgModule({
  declarations: [ ClientComponent, ClientRezervariComponent, RezervariComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    ClientRoutingModule,
    NgbModule
  ],
  entryComponents: [],
  exports: []
})
export class ClientModule { }
