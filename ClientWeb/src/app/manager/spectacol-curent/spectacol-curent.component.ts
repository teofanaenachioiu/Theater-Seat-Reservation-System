import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Spectacol} from '../../utils/Spectacol';
import {HttpErrorResponse} from '@angular/common/http';
import {AuthService} from '../../auth.service';
import {ShowsService} from '../../shows.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {NgbdModalAddContent} from '../manager-spectacole/modals/modal-add-component';
import {NgbdModalAddTodayShowContent} from './modal-add-today-component';

@Component({
  selector: 'app-spectacol-curent',
  templateUrl: './spectacol-curent.component.html',
  styleUrls: ['./spectacol-curent.component.css']
})
export class SpectacolCurentComponent implements OnInit, OnDestroy {

  subscriptions = [];
  items: Spectacol[];
  error: HttpErrorResponse;
  errorLabel: string;
  timpRamas: any;
  canAdd: boolean;

  constructor(private authService: AuthService, private itemService: ShowsService, private modalService: NgbModal, private router: Router) {
  }

  ngOnInit(): void {
    this.loadItems();
    this.itemService.loadTime();
  }

  async openAddTodayShow(item: Spectacol) {
    const modalRef = this.modalService.open(NgbdModalAddTodayShowContent);

    await this.itemService.loadTime();
    const day1 = new Date(parseInt(this.itemService.timp, 10)).getDay();
    const day2 = new Date().getDay();
    this.canAdd = day1 !== day2;
    this.timpRamas = new Date();
    this.timpRamas.setHours(0, 0, 0, 0);
    this.timpRamas = new Date(this.timpRamas.getTime() + 86400000);

    modalRef.componentInstance.item = item;
    modalRef.componentInstance.timpRamas = this.timpRamas.getTime();
    modalRef.componentInstance.canAdd = this.canAdd || this.itemService.timp === 0;
    modalRef.componentInstance.passEntry.subscribe((receivedEntry) => {
      const price = receivedEntry.price;
      const hour = receivedEntry.hour;
      const minutes = receivedEntry.minutes;
      this.itemService.saveTodayShow(item.id, price, hour, minutes);
      this.canAdd = false; // ca sa nu mai pot adauga
    });
  }

  loadItems(): void {
    this.subscriptions.push(this.itemService.getAll().subscribe(items => {
      this.items = items;
    }, error => this.error = error));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  logout() {
    this.authService.logoutManager()
      .subscribe((res) => {
        this.router.navigate([`/theater`]);
      });
  }
}
