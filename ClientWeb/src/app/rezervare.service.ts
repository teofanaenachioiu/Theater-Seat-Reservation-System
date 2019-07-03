import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {Rezervare} from './utils/Rezervare';
import {SpectacolData} from './utils/SpectacolData';

const rezervareURL = 'http://localhost:8080/rez/rezervare';
const rezervareSpectacolCurentURL = 'http://localhost:8080/rez/rezervareByDate';
const lastSpectacolURL = 'http://localhost:8080/spec/spectacolDataLastAll';

@Injectable({
  providedIn: 'root'
})
export class RezervareService {
  private itemSubject: BehaviorSubject<Rezervare[]>;

  httpOptions = {
    headers: new HttpHeaders({responseType: 'text' as 'json', 'Access-Control-Allow-Origin': '*'})
  };

  constructor(private httpClient: HttpClient, private router: Router) {
    this.itemSubject = new BehaviorSubject([]);
  }

  getAllRezervari(data: number, idSpectacol: number): Observable<Rezervare[]> {
    return this.httpClient.post<Rezervare[]>(rezervareSpectacolCurentURL, {data, idSpectacol}, this.httpOptions);
  }

  getLastSpectacol(): Observable<SpectacolData> {
    return this.httpClient.get<SpectacolData>(lastSpectacolURL, this.httpOptions);
  }

  addRezervare(idSpectacolData: number, idSpectator: string, nrScaun: number): Observable<Rezervare> {
    const pozitieX = nrScaun % 7;
    const pozitieY = Math.floor(nrScaun / 14) + 1;

    return this.httpClient.post<Rezervare>(rezervareURL, {
      pozitieX, pozitieY,
      nrScaun, idSpectacolData, idSpectator
    }, this.httpOptions);
  }

  getAll(spectator: string): Observable<Rezervare[]> {
    return this.httpClient.get<Rezervare[]>(rezervareURL + '/' + spectator, this.httpOptions)
      .pipe(tap(ite => this.itemSubject.next(ite)));
  }

  unsubscribe(id: string): void {
    this.httpClient.get('http://localhost:8080/rez/unsubscribe/' + id, this.httpOptions).subscribe();
  }

  delete(id: number): Observable<string> {
    return this.httpClient.delete<string>(`${rezervareURL}/${id}`, {responseType: 'text' as 'json'})
      .pipe(tap(() => {
        const items = this.itemSubject.getValue() || [];
        for (let i = items.length - 1; i >= 0; i--) {
          if (items[i].id === id) {
            items.splice(i, 1);
          }
        }
        this.itemSubject.next(items);
      }));
  }

}
