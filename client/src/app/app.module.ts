import { BrowserModule } from '@angular/platform-browser';
import {Injectable, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule, HttpRequest, HttpHandler, HttpInterceptor, HTTP_INTERCEPTORS} from '@angular/common/http';
import { StoreListComponent } from './templateComponents/store-list/store-list.component';
import {StoreService} from "./service/store-service.service";
import { StoreComponent } from './templateComponents/store/store.component';
import { AccountComponent } from './templateComponents/account/account.component';
import { LoginComponent } from './templateComponents/login/login.component';
import {AppService} from "./service/app-service.service";
import {HttpInterceptorService} from "./service/http-interceptor-service.service";
import { LogoutComponent } from './templateComponents/logout/logout.component';



@NgModule({
  declarations: [
    AppComponent,
    StoreListComponent,
    StoreComponent,
    AccountComponent,
    LoginComponent,
    LogoutComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [StoreService, AppService,  {
    provide: HTTP_INTERCEPTORS,
    useClass: HttpInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }


