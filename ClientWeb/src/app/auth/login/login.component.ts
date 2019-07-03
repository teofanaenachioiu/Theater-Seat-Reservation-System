import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;
  errorText: string;

  login() {
    console.log(this.username);
    console.log(this.password);
    if (this.username === '' || this.password === '') {
      this.errorText = 'Username and password fields are required';
    } else {


      if (this.username === 'manager') {
        this.authService.authenticate(this.username, this.password)
          .subscribe((res) => {
            this.errorText = '';
            this.router.navigate([`/manager`]);
          }, (error) => {
            this.username = null;
            this.password = null;
            this.errorText = error.error;
            console.log(error);
          });
      } else {
        this.authService.authenticateSpectator(this.username, this.password)
          .subscribe((res) => {
            this.errorText = '';
            this.router.navigate([`/client`]);
          }, (error) => {
            this.username = null;
            this.password = null;
            this.errorText = error.error;
            console.log(error);
          });
      }
    }
  }

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.password = '';
    this.username = '';
  }

}
