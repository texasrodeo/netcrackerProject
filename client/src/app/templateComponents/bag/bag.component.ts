import { Component, OnInit } from '@angular/core';
import {Account} from "../../model/account";
import {Observable, Subscription} from "rxjs";
import {StoreService} from "../../service/store-service.service";
import {ActivatedRoute} from "@angular/router";
import {TokenstorageService} from "../../service/tokenstorage.service";

@Component({
  selector: 'app-bag',
  templateUrl: './bag.component.html',
  styleUrls: ['./bag.component.css']
})
export class BagComponent implements OnInit {

  accounts: Account[]=[];
  currentUser: any;
  private querySubscription: Subscription;


  constructor(private storeService: StoreService, private route: ActivatedRoute, private tokenStorage: TokenstorageService) {
    // this.querySubscription = route.queryParams.subscribe(
    //   (queryParam: any) => {
    //     this.id = queryParam['id'];
    //   }
    // );
  }

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();

    this.storeService.getbag(this.currentUser.id).subscribe(data => {
      this.accounts = data;

    });
  }

  deleteFromBag(id: number) {
    this.storeService.deletefrombag(id).subscribe();
  }
}
