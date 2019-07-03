import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  username: string;
  password: string;
  rePassword: string;
  errorText: string;

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.password = '';
    this.rePassword = '';
    this.username = '';
  }

  signup() {
    console.log(this.username);
    console.log(this.password);
    if (this.username === '' || this.password === '' || this.rePassword === '') {
      this.errorText = 'Please fill all fields';
    } else {
      if (this.rePassword !== this.password) {
        this.errorText = 'Reintroduce password';
      } else {
        console.log('totul bine');
        this.authService.signup(this.username, this.password)
          .subscribe((res) => {
            console.log(res);
            this.errorText = '';
            this.router.navigate([`/theater`]);
          }, (error) => {
            this.username = null;
            this.password = null;
            this.rePassword = null;
            this.errorText = error.error;
            console.log(error);
          });
      }
    }
  }

}
