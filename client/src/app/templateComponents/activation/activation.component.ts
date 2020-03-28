import { Component, OnInit } from '@angular/core';
import {StoreService} from "../../service/store-service.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {switchMap} from "rxjs/operators";
import {AppService} from "../../service/app-service.service";
import {Observable, Subscription} from "rxjs";

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.css']
})
export class ActivationComponent implements OnInit {


  code: String
  private routeSubscription: Subscription;

  constructor(private appService: AppService, private route: ActivatedRoute) {


  }

  ngOnInit(): void {
      this.routeSubscription = this.route.params.subscribe(params=>this.code=params['code']);
    this.appService.activate(this.code).subscribe();
    // this.appService.activate(this.code);

  }



}
