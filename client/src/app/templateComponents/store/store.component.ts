import { Component, OnInit } from '@angular/core';
import { NgForm} from '@angular/forms';

import {StoreService} from '../../service/store-service.service';
import {Account} from '../../model/account';
import {Subscription} from 'rxjs';
import {ActivatedRoute} from '@angular/router';
import {TokenstorageService} from '../../service/tokenstorage.service';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {
  accounts: Account[] = [];
  header: string;
  id: string;
  currentUser: any;
  private querySubscription: Subscription;

  sort: string;
  from: string;
  to: string;


  constructor(private storeService: StoreService, private route: ActivatedRoute, private token: TokenstorageService) {
    this.querySubscription = route.queryParams.subscribe(
      (queryParam: any) => {
        this.id = queryParam.id;

      }
    );
    this.currentUser = this.token.getUser();
  }

  ngOnInit(): void {
    this.storeService.findAcc(this.id).subscribe(data => {
      this.accounts = data.accounts;
      this.header = data.header;
    });
  }

  submit(form: NgForm) {
    if (form.value.from && form.value.to || form.value.sort) {
    this.storeService.findAccWithParams(this.id, form.value.from, form.value.to, form.value.sort).subscribe(data => {
      this.accounts = data.accounts;
      this.header = data.header;
    });
    }
  }

  clear(form: NgForm) {
    this.storeService.findAcc(this.id).subscribe(data => {
      this.accounts = data.accounts;
      this.header = data.header;
    });
    form.resetForm();
  }
}
