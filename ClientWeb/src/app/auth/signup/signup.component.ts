import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
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
