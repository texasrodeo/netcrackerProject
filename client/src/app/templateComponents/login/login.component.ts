import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AppService} from '../../service/app-service.service';
import {TokenstorageService} from '../../service/tokenstorage.service';
import {AppComponent} from '../../app.component';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: AppService, private tokenStorage: TokenstorageService,
              private router: Router,
              private appC: AppComponent) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit() {
    this.authService.login(this.form).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        setTimeout(() => {
          this.appC.refresh();
          this.router.navigate(['/gamestores']);
        }, 2000);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }


}
