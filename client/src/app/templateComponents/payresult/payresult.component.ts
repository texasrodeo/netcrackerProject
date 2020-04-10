import { Component, OnInit } from '@angular/core';
import {Subscription} from 'rxjs';
import {ActivatedRoute} from '@angular/router';
import {StoreService} from '../../service/store-service.service';

@Component({
  selector: 'app-payresult',
  templateUrl: './payresult.component.html',
  styleUrls: ['./payresult.component.css']
})
export class PayresultComponent implements OnInit {
  success: string;
  private routeSubscription: Subscription;
  querySubscription: Subscription;
  paymentid: any;
  payerid: any;
  accountsId = [];
  message = '';

  constructor(private storeService: StoreService, private route: ActivatedRoute) {


  }
  ngOnInit(): void {
    this.routeSubscription = this.route.params.subscribe(params => this.success = params.success);
    if (this.success === 'success') {
      this.querySubscription = this.route.queryParams.subscribe(
        data => {
          this.payerid = data.PayerID;
          this.paymentid = data.paymentId;
          for (const d in data) {
            if (d.includes('id')) {
              this.accountsId.push(data[d]);
            }
          }
        }
      );
      this.storeService.successBuy(this.paymentid, this.payerid, this.accountsId).subscribe(
        data => {
          this.message = data.message;
        }
      );
    }


  }

}
