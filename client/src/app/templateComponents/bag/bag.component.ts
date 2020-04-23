import { Component, OnInit } from '@angular/core';
import {Account} from '../../model/account';
import {Subscription} from 'rxjs';
import {StoreService} from '../../service/store-service.service';
import {ActivatedRoute} from '@angular/router';
import {TokenstorageService} from '../../service/tokenstorage.service';

@Component({
  selector: 'app-bag',
  templateUrl: './bag.component.html',
  styleUrls: ['./bag.component.css']
})
export class BagComponent implements OnInit {

  accounts: Account[] = [];
  sum: number;
  currentUser: any;
  message: string;
  constructor(private storeService: StoreService, private route: ActivatedRoute, private tokenStorage: TokenstorageService) {

  }

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    this.storeService.getbag(this.currentUser.id).subscribe(data => {
      this.accounts = data.accounts;
      this.sum = data.sum;
    });

  }

  deleteFromBag(id: number) {
     this.storeService.deletefrombag(id).subscribe();
     this.reloadPage();
  }

  reloadPage() {
    window.location.reload();
  }

  clearBag() {
    const accountsId = [];
    for (const account of this.accounts) {
      accountsId.push(account.id);
    }
    this.storeService.clearBag(accountsId).subscribe();
    this.reloadPage();
  }
}