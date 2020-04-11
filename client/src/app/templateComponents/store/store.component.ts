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
  message: string;
  page = 0;
  pages: Array<number>;
  size = 5;

  sort: string;
  from: any;
  to: string;


  constructor(private storeService: StoreService, private route: ActivatedRoute, private token: TokenstorageService) {

  }

  ngOnInit(): void {
    this.querySubscription = this.route.queryParams.subscribe(
      (queryParam: any) => {
        this.id = queryParam.id;
      }
    );
    this.currentUser = this.token.getUser();
    if (this.sort || this.from && this.to) {
      this.storeService.findAccWithParams(this.id, this.from, this.to, this.sort, this.page, this.size).subscribe(data => {
        this.accounts = data.accounts.content;
        this.header = data.header;
        this.pages = new Array(data.accounts.totalPages);
      });
    } else {
      this.storeService.findAcc(this.id, this.page, this.size).subscribe(data => {
        this.accounts = data.accounts.content;
        this.pages = new Array(data.accounts.totalPages);
        this.header = data.header;
      });
    }
  }

  submit(form: NgForm) {
    if (form.value.from && form.value.to || form.value.sort) {
      this.sort = form.value.sort;
      this.from = form.value.from;
      this.to = form.value.to;
      this.storeService.findAccWithParams(this.id, this.from, this.to, this.sort, this.page, this.size).subscribe(data => {
        this.accounts = data.accounts.content;
        this.pages = new Array(data.accounts.totalPages);
        this.header = data.header;
    });
    }
  }

  clear(form: NgForm) {
    this.sort = '';
    this.from = '';
    this.to = '';
    this.storeService.findAcc(this.id, this.page, this.size).subscribe(data => {
      this.accounts = data.accounts.content;
      this.pages = new Array(data.accounts.totalPages);
      this.header = data.header;
    });
    form.resetForm();
  }

  deleteAccount(id: number) {
    this.storeService.deleteAccount(id).subscribe(
      data => {
        this.message = data.message;
      }
    );
    this.ngOnInit();

  }

  setPage(i: number, event: any) {
    event.preventDefault();
    this.page = i;
    this.ngOnInit();
  }
}
