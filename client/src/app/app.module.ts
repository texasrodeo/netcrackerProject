import { BrowserModule } from '@angular/platform-browser';
import {Injectable, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule, HttpRequest, HttpHandler, HttpInterceptor, HTTP_INTERCEPTORS} from '@angular/common/http';
import { StoreListComponent } from './templateComponents/store-list/store-list.component';
import {StoreService} from './service/store-service.service';
import { StoreComponent } from './templateComponents/store/store.component';
import { AccountComponent } from './templateComponents/account/account.component';
import { LoginComponent } from './templateComponents/login/login.component';
import {AppService} from './service/app-service.service';




import { RegisterComponent } from './templateComponents/register/register.component';
import {authInterceptorProviders} from './config/auth.interseptor';
import { ProfileComponent } from './templateComponents/profile/profile.component';
import { ActivationComponent } from './templateComponents/activation/activation.component';
import { BagComponent } from './templateComponents/bag/bag.component';
import { CheckoutComponent } from './templateComponents/checkout/checkout.component';
import { AddAccountComponent } from './templateComponents/add-account/add-account.component';
import { ForbiddenComponent } from './templateComponents/forbidden/forbidden.component';
import { PayresultComponent } from './templateComponents/payresult/payresult.component';
import { HomeComponent } from './templateComponents/home/home.component';
import {ToastrModule} from 'ngx-toastr';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';



@NgModule({
  declarations: [
    AppComponent,
    StoreListComponent,
    StoreComponent,
    AccountComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    ActivationComponent,
    BagComponent,
    CheckoutComponent,
    AddAccountComponent,
    ForbiddenComponent,
    PayresultComponent,
    HomeComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [StoreService, AppService,  authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }


