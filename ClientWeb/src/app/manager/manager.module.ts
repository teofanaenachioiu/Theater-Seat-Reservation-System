import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {ManagerComponent} from './manager.component';
import {ManagerRoutingModule} from './manager-routing.module';
import {ManagerSpectacoleComponent} from './manager-spectacole/manager-spectacole.component';
import {NgbdModalAddContent} from './manager-spectacole/modals/modal-add-component';
import {NgbdModalUpdateContent} from './manager-spectacole/modals/modal-update-component';
import {NgbdModalDeleteContent} from './manager-spectacole/modals/modal-component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {SpectacolCurentComponent} from './spectacol-curent/spectacol-curent.component';
import {NgbdModalAddTodayShowContent} from './spectacol-curent/modal-add-today-component';
import {CountdownModule} from 'ngx-countdown';

@NgModule({
  declarations: [
    ManagerComponent,
    ManagerSpectacoleComponent,
    NgbdModalAddContent,
    NgbdModalDeleteContent,
    NgbdModalUpdateContent,
    NgbdModalAddTodayShowContent,
  SpectacolCurentComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    ManagerRoutingModule,
    NgbModule,
    CountdownModule
  ],
  entryComponents: [NgbdModalAddContent, NgbdModalDeleteContent, NgbdModalUpdateContent, NgbdModalAddTodayShowContent],
  exports: [NgbdModalAddContent, NgbdModalDeleteContent, NgbdModalUpdateContent, NgbdModalAddTodayShowContent]
})
export class ManagerModule { }
