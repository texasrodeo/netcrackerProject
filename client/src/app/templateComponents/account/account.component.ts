import { Component, OnInit } from '@angular/core';
import {Subscription} from 'rxjs';
import {StoreService} from '../../service/store-service.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Account} from '../../model/account';
import {TokenstorageService} from '../../service/tokenstorage.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
   styleUrls: ['./account.component.css']

})
export class AccountComponent implements OnInit {

  id: string;
  account: Account;
  private querySubscription: Subscription;
  currentUser: any;
  message: string;
  added = false;

  constructor(private storeService: StoreService,
              private route: ActivatedRoute,
              private tokenStorage: TokenstorageService,
              private router: Router) {
    this.querySubscription = route.queryParams.subscribe(
      (queryParam: any) => {
        this.id = queryParam.id;
      }
    );
  }

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();

    this.storeService.findAccount(this.id).subscribe(data => {
      this.account = data;
    });
  }

  addToCart() {
    if (!this.tokenStorage.getUser()) {
      this.router.navigateByUrl('/forbidden');
    } else {
    this.storeService.addtocart(this.account.id).subscribe(data => {
      this.message = data.message;
      this.added = true;
    } );
    }

  }

  reloadPage() {
    window.location.reload();
  }

}
