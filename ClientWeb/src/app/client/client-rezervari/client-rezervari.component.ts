import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from '../../auth.service';
import {Router} from '@angular/router';
import {Rezervare} from '../../utils/Rezervare';
import {RezervareService} from '../../rezervare.service';

@Component({
  selector: 'app-client-rezervari',
  templateUrl: './client-rezervari.component.html',
  styleUrls: ['./client-rezervari.component.css']
})
export class ClientRezervariComponent implements OnInit, OnDestroy {
  subscriptions = [];
  items: Rezervare[];
  timpulCurent: number;

  constructor(private rezervareService: RezervareService, private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.loadItems();
    this.timpulCurent = new Date().getTime();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  loadItems(): void {
    const spectator = this.authService.spectator;
    this.subscriptions.push(this.rezervareService.getAll(spectator).subscribe(items => {
      this.items = items;
    }));
  }

  logout() {
    this.authService.logoutSpectator()
      .subscribe((res) => {
        this.router.navigate([`/theater`]);
      });
  }


  deleteReservation(id: number) {
    this.subscriptions.push(this.rezervareService.delete(id).subscribe());
  }
}
