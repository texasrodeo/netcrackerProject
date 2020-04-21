import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-forbidden',
  templateUrl: './forbidden.component.html',
  styleUrls: ['./forbidden.component.css']
})
export class ForbiddenComponent implements OnInit {

  querySubscription: Subscription;
  code: string;
  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.querySubscription = this.route.queryParams.subscribe(
      (queryParam: any) => {
        this.code = queryParam.code;
      }
    );
  }

}
