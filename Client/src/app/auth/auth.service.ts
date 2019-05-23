import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const authURL = 'http://localhost:8080/theater/auth';

interface AuthResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private httpClient: HttpClient) {
  }

  authenticate(username: string, password: string): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(authURL + '/login', {username, password}, this.httpOptions);
  }
}
