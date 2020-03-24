import { Component, OnInit } from '@angular/core';
import {StoreService} from "../../service/store-service.service";
import {Game} from "../../model/game";

@Component({
  selector: 'app-store-list',
  templateUrl: './store-list.component.html',
  styleUrls: ['./store-list.component.css']
})
export class StoreListComponent implements OnInit {

  stores: Game[];

  constructor(private storeService: StoreService) { }

  ngOnInit(): void {
    this.storeService.findAll().subscribe(data => {
      this.stores = data;
    });
  }

}
