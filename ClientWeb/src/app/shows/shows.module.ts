import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {ShowsService} from '../shows.service';
import {ShowsComponent} from './shows.component';
import {ShowsRoutingModule} from './shows-routing.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [ShowsComponent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    ShowsRoutingModule,
    NgbModule
  ],
  providers: []
})
export class ShowsModule { }
