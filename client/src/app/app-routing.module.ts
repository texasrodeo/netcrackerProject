import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StoreListComponent } from './templateComponents/store-list/store-list.component';
import {StoreComponent} from './templateComponents/store/store.component';
import {AccountComponent} from './templateComponents/account/account.component';
import {LoginComponent} from './templateComponents/login/login.component';
import {RegisterComponent} from './templateComponents/register/register.component';
import {ProfileComponent} from './templateComponents/profile/profile.component';
import {ActivationComponent} from './templateComponents/activation/activation.component';
import {BagComponent} from './templateComponents/bag/bag.component';
import {CheckoutComponent} from './templateComponents/checkout/checkout.component';
import {AddAccountComponent} from './templateComponents/add-account/add-account.component';
import {ForbiddenComponent} from './templateComponents/forbidden/forbidden.component';
import {PayresultComponent} from './templateComponents/payresult/payresult.component';
import {HomeComponent} from "./templateComponents/home/home.component";


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'gamestore/account', component: AccountComponent },
  { path: 'gamestores', component: StoreListComponent},
  { path: 'gamestore', component: StoreComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  {path: 'activate/:code', component: ActivationComponent},
  {path: 'bag', component: BagComponent},
  {path: 'checkout', component: CheckoutComponent},
  {path: 'addAccount', component: AddAccountComponent},
  {path: 'forbidden', component: ForbiddenComponent},
  {path: 'pay/:success', component: PayresultComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
