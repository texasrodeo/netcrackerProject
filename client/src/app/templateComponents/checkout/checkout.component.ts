import { Component, OnInit } from '@angular/core';
import {Account} from '../../model/account';
import {StoreService} from '../../service/store-service.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  accounts: Account[] = [];
  sum: number;
  error: '';

  constructor(private router: Router, private storeService: StoreService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.storeService.checkout().subscribe(data => {
      this.accounts = data.items;
      this.sum = data.sum;
      if (data.autherror) {
        this.router.navigate(['/forbidden'], {queryParams: {code: 'noemail'}});
      }
    });

  }

  buy(sum: number) {
    this.storeService.buy(sum);

  }

}
