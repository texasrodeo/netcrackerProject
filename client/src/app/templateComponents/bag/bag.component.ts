import { Component, OnInit } from '@angular/core';
import {Account} from '../../model/account';
import {Observable, Subscription} from 'rxjs';
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
  private querySubscription: Subscription;


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
     this.storeService.deletefrombag(id).subscribe(data => {
         this.message = data;
     }
     );
     this.reloadPage();
  }
  reloadPage() {
    window.location.reload();
  }
}
