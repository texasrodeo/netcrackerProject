import { Component, OnInit } from '@angular/core';
import {StoreService} from "../../service/store-service.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {switchMap} from "rxjs/operators";
import {AppService} from "../../service/app-service.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.css']
})
export class ActivationComponent implements OnInit {



  constructor(private appService: AppService, private route: ActivatedRoute) {

  }

  ngOnInit(): void {

      this.route.paramMap.pipe(
      switchMap((params: ParamMap) => this.appService.activate(params.get('code')))
    );
  }



}
