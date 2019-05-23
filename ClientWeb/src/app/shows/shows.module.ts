import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {ShowsService} from './shows.service';
import {ShowsComponent} from './shows.component';
import {ShowsRoutingModule} from './shows-routing.module';
import {ShowsManagerComponent} from './shows-manager.component';
import {HomeManagerComponent} from './home-manager/home-manager.component';
import {NgbdModalDeleteContent} from './modal-component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {NgbdModalAddContent} from './modal-add-component';
import {NgbdModalUpdateContent} from './modal-update-component';


@NgModule({
  declarations: [ShowsComponent, ShowsManagerComponent, HomeManagerComponent, NgbdModalDeleteContent, NgbdModalAddContent, NgbdModalUpdateContent],
  entryComponents: [NgbdModalDeleteContent, NgbdModalAddContent, NgbdModalUpdateContent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    ShowsRoutingModule,
    NgbModule
  ],
  providers: [ShowsService]
})
export class ShowsModule { }
