import {Component, OnDestroy, OnInit} from '@angular/core';
import {Spectacol} from '../../utils/Spectacol';
import {HttpErrorResponse} from '@angular/common/http';
import {ShowsService} from '../../shows.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {AuthService} from '../../auth.service';
import {NgbdModalUpdateContent} from './modals/modal-update-component';
import {NgbdModalAddContent} from './modals/modal-add-component';
import {NgbdModalDeleteContent} from './modals/modal-component';

@Component({
  selector: 'app-manager-spectacole',
  templateUrl: './manager-spectacole.component.html',
  styleUrls: ['./manager-spectacole.component.css']
})
export class ManagerSpectacoleComponent implements OnInit, OnDestroy {

  subscriptions = [];
  spectacole: Spectacol[];
  error: HttpErrorResponse;

  constructor(private authService: AuthService, private itemService: ShowsService, private modalService: NgbModal, private router: Router) {
  }

  ngOnInit(): void {
    this.loadItems();
  }

  loadItems(): void {
    this.subscriptions.push(this.itemService.getAll().subscribe(items => {
      this.spectacole = items;
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

  logout() {
    this.authService.logoutManager()
      .subscribe((res) => {
        this.router.navigate([`/theater`]);
      });
  }

}
