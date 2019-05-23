import {Component, OnDestroy, OnInit} from '@angular/core';
import {Spectacol} from './Spectacol';
import {HttpErrorResponse} from '@angular/common/http';
import {ShowsService} from './shows.service';

import {Input} from '@angular/core';
import {NgbActiveModal, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {NgbdModalDeleteContent} from './modal-component';
import {NgbdModalAddContent} from './modal-add-component';
import {NgbdModalUpdateContent} from './modal-update-component';


@Component({
  selector: 'app-shows-manager',
  templateUrl: './shows-manager.component.html',
  styleUrls: ['./shows-manager.component.css']
})
export class ShowsManagerComponent implements OnInit, OnDestroy {

  subscriptions = [];
  items: Spectacol[];
  error: HttpErrorResponse;

  constructor(private itemService: ShowsService, private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.loadItems();
  }

  loadItems(): void {
    this.subscriptions.push(this.itemService.getAll().subscribe(items => {
      this.items = items;
    }, error => this.error = error));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  openDelete(spectacol: Spectacol) {
    const modalRef = this.modalService.open(NgbdModalDeleteContent);
    modalRef.componentInstance.name = spectacol.denumire;
    modalRef.componentInstance.passEntry.subscribe((receivedEntry) => {
      if (receivedEntry === 'delete') {
        this.subscriptions.push(this.itemService.delete(spectacol.id).subscribe());
      }
    });
  }

  openAdd() {
    const modalRef = this.modalService.open(NgbdModalAddContent);
    modalRef.componentInstance.passEntry.subscribe((receivedEntry) => {
      const show = new Spectacol(receivedEntry.namee, receivedEntry.descriptionn);
      this.subscriptions.push(this.itemService.save(show).subscribe());
    });
  }

  openUpdate(item: Spectacol) {
    const modalRef = this.modalService.open(NgbdModalUpdateContent);
    modalRef.componentInstance.item = item;
    modalRef.componentInstance.passEntry.subscribe((receivedEntry) => {
      const show = new Spectacol(receivedEntry.namee, receivedEntry.descriptionn);
      show.id = receivedEntry.idd;
      this.subscriptions.push(this.itemService.update(show).subscribe());
    });
  }
}
