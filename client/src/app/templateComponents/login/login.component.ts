import { Component, OnInit } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {AppService} from "../../service/app-service.service";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials = {username: '', password: ''};

  username: string;
  password: string;
  errorMessage = 'Invalid Credentials';
  successMessage: string;
  invalidLogin = false;
  loginSuccess = false;

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
  }

  // login() {
  //   this.app.authenticate(this.credentials, () => {
  //     this.router.navigateByUrl('/');
  //   });
  //   return false;
  // }

  handleLogin() {
    this.app.authenticationService(this.username, this.password).subscribe((result) => {
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Успешно вошли.';
      this.router.navigate(['/']);
    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
    });
  }

  ngOnInit(): void {
  }

}
