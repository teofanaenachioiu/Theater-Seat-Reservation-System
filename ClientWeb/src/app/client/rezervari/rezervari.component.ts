import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from '../../auth.service';
import {Router} from '@angular/router';
import {Spectacol} from '../../utils/Spectacol';
import {SpectacolData} from '../../utils/SpectacolData';
import {Rezervare} from '../../utils/Rezervare';
import {RezervareService} from '../../rezervare.service';
import {ShowsService} from '../../shows.service';

@Component({
  selector: 'app-rezervari',
  templateUrl: './rezervari.component.html',
  styleUrls: ['./rezervari.component.css']
})
export class RezervariComponent implements OnInit, OnDestroy {

  listaRezervariDorite: Array<number>;
  listaRezervariFacute: Array<Rezervare>;
  idSpectacolData: number;
  spectacolData: SpectacolData;
  spectacol: Spectacol;
  rezervarePosibila: Rezervare;

  constructor(private rezervariService: RezervareService, private authService: AuthService, private router: Router) {
  }

  async loadItems() {
    this.listaRezervariDorite = new Array<number>();
    await this.rezervariService.getLastSpectacol().subscribe(x => {
        this.spectacolData = x;
        this.idSpectacolData = x.id;
        this.rezervariService.getAllRezervari(x.data, x.spectacolId)
          .subscribe(y => {
            this.listaRezervariFacute = y;
            for (let i = 0; i < this.listaRezervariFacute.length; i++) {
              this.spectacol = new Spectacol(this.listaRezervariFacute[i].denumire, this.listaRezervariFacute[i].descriere);
              const myId = this.listaRezervariFacute[i].nrScaun.toString();
              document.getElementById(myId).style.background = 'rgba(255, 255, 255, 1)';
            }
          });
        this.ascultaEventuri();
      }
    );
  }

  async ngOnInit() {
    this.loadItems();
  }

  ngOnDestroy(): void {
    this.rezervariService.unsubscribe(this.authService.spectator);
  }


  mouseEnter(nrScaun: number) {
    console.log('mouse enter : ' + nrScaun);
    const pozX = nrScaun % 7 + 1;
    const pozY = Math.floor(nrScaun / 14) + 1;
    this.rezervarePosibila = new Rezervare(this.spectacol.denumire, this.spectacol.descriere,
      this.spectacolData.data, this.spectacolData.pret, pozX, pozY, nrScaun);
  }

  logout() {
    this.authService.logoutSpectator()
      .subscribe((res) => {
        this.router.navigate([`/theater`]);
      });
  }

  verificaDacaExistaDorit(idDiv: number): boolean {
    for (let i = 0; i < this.listaRezervariDorite.length; i++) {
      if (this.listaRezervariDorite[i] === idDiv) {
        return true;
      }
    }
    return false;
  }

  verificaDacaExistaRezervat(idDiv: number): boolean {
    for (let i = 0; i < this.listaRezervariFacute.length; i++) {
      if (this.listaRezervariFacute[i].nrScaun === idDiv) {
        return true;
      }
    }
    return false;
  }

  addToList(idDiv: number) {
    if (!this.verificaDacaExistaDorit(idDiv) && !this.verificaDacaExistaRezervat(idDiv)) {
      this.listaRezervariDorite.push(idDiv);
      const myId = idDiv.toString();
      document.getElementById(myId).style.background = 'pink';
    } else if (!this.verificaDacaExistaRezervat(idDiv)) {
      for (let i = this.listaRezervariDorite.length - 1; i >= 0; i--) {
        if (this.listaRezervariDorite[i] === idDiv) {
          this.listaRezervariDorite.splice(i, 1);
          const myId = idDiv.toString();
          document.getElementById(myId).style.background =
            'linear-gradient(to top, #761818, #761818, #761818, #761818, #761818, #B54041,  #F3686A)';
        }
      }
    }
  }

  rezerva() {
    for (let i = 0; i < this.listaRezervariDorite.length; i++) {
      console.log('Id Spectacol Data ', this.idSpectacolData);
      console.log('Id spectator ', this.authService.spectator);
      console.log('Nr scaun', this.listaRezervariDorite[i]);
      this.rezervariService.addRezervare(this.idSpectacolData, this.authService.spectator, this.listaRezervariDorite[i])
        .subscribe(rez => {
        }, err => {
          console.log(err);
          alert('seat nr ' + this.listaRezervariDorite[i] + ' already reservated');
        });
    }
    this.coloreazaScaune();
  }

  coloreazaScaune() {
    for (let i = 0; i < this.listaRezervariDorite.length; i++) {
      const myId = this.listaRezervariDorite[i].toString();
      document.getElementById(myId).style.background = 'rgba(255, 255, 255, 1)';
    }
    this.listaRezervariDorite = new Array<number>();
  }

  ascultaEventuri() {
    const source = new EventSource('http://localhost:8080/rez/subscribe/' + this.authService.spectator);

    source.onmessage = (event) => {
      const json = JSON.parse(event.data);
      const rez = new Rezervare(json.denumire, json.descriere, json.data,
        json.pret, json.pozitieX, json.pozitieY, json.nrScaun);
      rez.id = json.id;

      let exista = false;

      for (let i = this.listaRezervariFacute.length - 1; i >= 0; i--) {
        if (this.listaRezervariFacute[i].id === rez.id) {
          this.listaRezervariFacute.splice(i, 1);
          exista = true;
          const myId = rez.nrScaun.toString();
          document.getElementById(myId).style.background =
            'linear-gradient(to top, #761818, #761818, #761818, #761818, #761818, #B54041,  #F3686A)';
        }
      }

      if (!exista) {
        this.listaRezervariFacute.push(rez);
        const myId = json.nrScaun;
        document.getElementById(myId).style.background = 'rgba(255, 255, 255, 1)';
      }
    };
  }
}
