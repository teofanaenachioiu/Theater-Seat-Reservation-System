import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';

const authURL = 'http://localhost:8080/theater/auth';

interface AuthResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  httpOptions = {
    // headers: new HttpHeaders({responseType: 'text'})
    headers: new HttpHeaders({'Content-Type': 'application/json' })
  };

  constructor(private httpClient: HttpClient) {
  }

  authenticate(username: string, password: string): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(authURL + '/login', {username, password}, {responseType: 'text' as 'json'})
      .pipe(
        tap(response => localStorage.setItem('token', response.token))
      );
  }

  signup(username: string, password: string, rePassword): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(authURL + '/signup', {username, password, rePassword}, this.httpOptions);
  }
}

