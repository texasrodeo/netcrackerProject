import {Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {AppService} from "./service/app-service.service";
import {ActivatedRoute, Router} from "@angular/router";
import { finalize } from 'rxjs/operators'

@Component({
  selector: 'app-root',


  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title: string;
 // stores:"stores";

  isLoggedIn = false;

  constructor(private http:HttpClient, private app:AppService, private router:Router, private route: ActivatedRoute,) {
    this.title = 'Магазин продажи игровых аккаунтов';

  }
  // logout() {
  //   this.http.post('login',{}).pipe(finalize(() => {
  //     this.app.authenticated = false;
  //     this.router.navigateByUrl('/login');
  //   })).subscribe();
  // }
  authenticated() { return this.app.authenticated; }
  handleLogout() {
    this.app.logout();
  }
  ngOnInit(){
    this.isLoggedIn = this.app.isUserLoggedIn();
    console.log('menu ->' + this.isLoggedIn);

  }
}



