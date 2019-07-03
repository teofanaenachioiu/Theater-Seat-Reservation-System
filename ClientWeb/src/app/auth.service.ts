import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';

const authURL = 'http://localhost:8080/auth';

interface UserResponse {
  id: string;
  hash: string;
  tip: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private httpClient: HttpClient) {
  }

  manager: string;
  spectator: string;


  authenticate(username: string, password: string): Observable<string> {
    this.manager = username;
    return this.httpClient.post<string>(authURL + '/loginManager', {username, password}, {responseType: 'text' as 'json'})
      .pipe();
  }

  signup(username: string, password: string): Observable<string> {
    console.log('am intrat la signup');
    return this.httpClient.post<string>(authURL + '/signup', {username, password}, {responseType: 'text' as 'json'})
      .pipe();
  }

  logoutManager(): Observable<string> {
    const username = this.manager;
    return this.httpClient.post<string>(authURL + '/logoutManager', {username}, {responseType: 'text' as 'json'})
      .pipe();
  }

  logoutSpectator(): Observable<string> {
    const username = this.spectator;
    return this.httpClient.post<string>(authURL + '/logoutSpectator', {username}, {responseType: 'text' as 'json'})
      .pipe();
  }

  authenticateSpectator(username: string, password: string): Observable<string> {
    this.spectator = username;
    return this.httpClient.post<string>(authURL + '/loginSpectator', {username, password}, {responseType: 'text' as 'json'})
      .pipe();
  }
}

