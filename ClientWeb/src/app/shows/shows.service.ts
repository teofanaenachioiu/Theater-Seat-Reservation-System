import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {retry, tap} from 'rxjs/operators';
import {Spectacol} from './Spectacol';

const showURL = 'http://localhost:8080/theater/shows';

interface AuthResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class ShowsService {
  private itemSubject: BehaviorSubject<Spectacol[]>;

  httpOptions = {
    headers: new HttpHeaders({responseType: 'text' as 'json', 'Access-Control-Allow-Origin': '*'})
  };

  constructor(private httpClient: HttpClient) {
    this.itemSubject = new BehaviorSubject([]);
  }

  getAll(): Observable<Spectacol[]> {
    return this.httpClient.get<Spectacol[]>(showURL, this.httpOptions)
      .pipe(tap(ite => this.itemSubject.next(ite)));
  }

  delete(id: number): Observable<string> {
    return this.httpClient.delete<string>(`${showURL}/${id}`, this.httpOptions)
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
    return this.httpClient.put<Spectacol>(`${showURL}/${item.id}`, item, {responseType: 'text' as 'json'})
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
