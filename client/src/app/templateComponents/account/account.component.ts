import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
import {StoreService} from "../../service/store-service.service";
import {ActivatedRoute} from "@angular/router";
import {Account} from "../../model/account";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  id:string;
  account: Account;
  private querySubscription: Subscription;


  constructor(private storeService: StoreService, private route: ActivatedRoute) {
    this.querySubscription = route.queryParams.subscribe(
      (queryParam: any) => {
        this.id = queryParam['id'];
      }
    );
  }

  ngOnInit(): void {
    this.storeService.findAccount(this.id).subscribe(data => {
      this.account = data;
    });
  }

}
