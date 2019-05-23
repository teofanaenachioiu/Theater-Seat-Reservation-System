import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth.service';
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
      this.authService.authenticate(this.username, this.password)
        .subscribe((res) => {
          this.errorText = '';
          console.log(res);
          this.router.navigate(['/theater/shows/manager']);
          console.log('gata');
        }, (error) => {
          this.username = null;
          this.password = null;
          this.errorText = error.error;
          console.log(error);
        });
    }
  }

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.password = '';
    this.username = '';
  }

}
