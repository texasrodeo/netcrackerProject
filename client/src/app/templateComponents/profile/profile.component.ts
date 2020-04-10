import { Component, OnInit } from '@angular/core';
import {TokenstorageService} from '../../service/tokenstorage.service';
import {AppService} from '../../service/app-service.service';
import {StoreService} from '../../service/store-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser: any;
  emailConfirmed = false;
  message: string;
  purchases: any;

  constructor(private token: TokenstorageService, private app: AppService, private storeService: StoreService) { }

  ngOnInit() {
    this.currentUser = this.token.getUser();
    this.app.checkEmail(this.currentUser.id).subscribe(
      data => {
        this.emailConfirmed = data.res;
      }
    );
    this.storeService.findPurchases(this.currentUser.id).subscribe(
      data => {
        this.purchases = data.items;
        this.message = data.message;
      }
    );
  }



}
