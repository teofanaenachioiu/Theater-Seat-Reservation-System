import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;
  login() {
    this.authService.authenticate(this.username, this.password)
      .subscribe((res) => {
        console.log(res);
      }, (error) => {
        console.log(error);
      });
  }
  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

}
