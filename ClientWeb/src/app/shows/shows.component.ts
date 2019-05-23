import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {Spectacol} from './Spectacol';
import {ShowsService} from './shows.service';

@Component({
  selector: 'app-shows',
  templateUrl: './shows.component.html',
  styleUrls: ['./shows.component.css']
})
export class ShowsComponent implements OnInit, OnDestroy {

  subscriptions = [];
  items: Spectacol[];
  error: HttpErrorResponse;
  constructor(private itemService: ShowsService) {  }

  ngOnInit(): void {
    this.loadItems();
  }

  loadItems(): void {
    this.subscriptions.push(this.itemService.getAll().subscribe(items => {
      this.items = items;
    }, error => this.error = error));
  }

  retry() {
    this.loadItems();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}
