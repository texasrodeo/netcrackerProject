import {Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {AppService} from './service/app-service.service';
import {ActivatedRoute, Router} from '@angular/router';
import { finalize } from 'rxjs/operators';
import {TokenstorageService} from './service/tokenstorage.service';

@Component({
  selector: 'app-root',


  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title: string;
  private roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  username: string;

  constructor(private tokenStorageService: TokenstorageService, private http: HttpClient, private app: AppService, private router: Router, private route: ActivatedRoute) {
    this.title = 'Магазин продажи игровых аккаунтов';
  }

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');


      this.username = user.username;
    }
  }

  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}



