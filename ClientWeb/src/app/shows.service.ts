import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Spectacol} from './utils/Spectacol';
import {Router} from '@angular/router';

const showURL = 'http://localhost:8080/spec/spectacol';
const showTodayURL = 'http://localhost:8080/spec/spectacolData';

interface AuthResponse {
  token: string;
}


@Injectable({
  providedIn: 'root'
})
export class ShowsService {
  timp: any;
  private itemSubject: BehaviorSubject<Spectacol[]>;

  httpOptionsJson = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  httpOptions = {
    headers: new HttpHeaders({responseType: 'text' as 'json', 'Access-Control-Allow-Origin': '*'})
  };

  loadTime() {
    this.httpClient.get<number>('http://localhost:8080/spec/spectacolDataLast', {responseType: 'text' as 'json'})
      .pipe(tap(ite => this.timp = ite, err => console.log(err)))
      .subscribe();
  }

  constructor(private httpClient: HttpClient, private router: Router) {
    this.itemSubject = new BehaviorSubject([]);
  }

  saveTodayShow(idSpectacol: number, pret: number, hours: number, minutes: number): void {
    let data = new Date().setHours(0, 0, 0, 0);
    console.log('hours ', hours);
    console.log('minutes ', minutes);
    console.log('data ', data);
    data = data + hours * 3600000 + minutes * 60000;

    console.log('dataDupa ', data);

    this.httpClient.post<string>(showTodayURL, {idSpectacol, pret, data}, {responseType: 'text' as 'json'})
      .subscribe();

  }

  getAll(): Observable<Spectacol[]> {
    return this.httpClient.get<Spectacol[]>(showURL, this.httpOptions)
      .pipe(tap(ite => this.itemSubject.next(ite)));
  }

  delete(id: number): Observable<string> {
    return this.httpClient.delete<string>(`${showURL}/${id}`, {responseType: 'text' as 'json'})
      .pipe(tap(() => {
        const items = this.itemSubject.getValue() || [];
        for (let i = items.length - 1; i >= 0; i--) {
          if (items[i].id === id) {
            items.splice(i, 1);
          }
        }
        console.log(items);
        this.itemSubject.next(items);
      }));
  }

  save(item: Spectacol): Observable<string> {
    return this.httpClient.post<string>(showURL, item, {responseType: 'text' as 'json'})
      .pipe(tap((elem) => {
        console.log(typeof (elem));
        const show = JSON.parse(elem);
        console.log(show);
        const items = this.itemSubject.getValue() || [];
        const spec = new Spectacol(show.denumire, show.descriere);
        spec.id = show.id;
        items.push(spec);
        this.itemSubject.next(items);
      }));
  }

  update(item: Spectacol): Observable<Spectacol> {
    return this.httpClient.put<Spectacol>(showURL, item, {responseType: 'text' as 'json'})
      .pipe(tap(() => {
        const items = this.itemSubject.getValue();
        for (let i = 0; i < items.length; i++) {
          if (items[i].id === item.id) {
            Object.assign(items[i], item);
          }
        }
        this.itemSubject.next(items);
      }));
  }

}
