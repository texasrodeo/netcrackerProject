import { Component, OnInit } from '@angular/core';
import {Account} from "../../model/account";
import {StoreService} from "../../service/store-service.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  accounts: Account[]=[];
  sum : number;
  error: string;

  constructor(private storeService: StoreService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.storeService.checkout().subscribe(data => {
      this.accounts = data["accounts"];
      this.sum = data["sum"];
      this.error = data["autherror"];
    });
  }

  buy(sum: number){
    this.storeService.buy(sum);
    //   .subscribe(
  //     data=>{
  //     this.error = "";
  // },
  //     err=>{
  //       this.error = err.error.message;
  //     }
  //   );
  }

}
