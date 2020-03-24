import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StoreListComponent } from './templateComponents/store-list/store-list.component';
import {StoreComponent} from "./templateComponents/store/store.component";
import {AccountComponent} from "./templateComponents/account/account.component";
import {LoginComponent} from "./templateComponents/login/login.component";


const routes: Routes = [
  {path: 'gamestore/account', component: AccountComponent },
  { path: 'gamestores', component: StoreListComponent},
  { path: 'gamestore', component: StoreComponent},
  {path:'login', component: LoginComponent},
  {path:'logout', component: LoginComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
