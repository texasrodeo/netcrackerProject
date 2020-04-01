import { Component, OnInit } from '@angular/core';
import {Game} from "../../model/game";
import {StoreService} from "../../service/store-service.service";
import {TokenstorageService} from "../../service/tokenstorage.service";

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent implements OnInit {

  stores: Game[];
  form: any = {};
  currentUser: any;

  message: string;

  constructor(private storeService:StoreService, private token: TokenstorageService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    if(this.currentUser.role === "ROLE_ADMIN"){
      this.storeService.findAll().subscribe(data => {
        this.stores = data;
      });
    }
  }

  onSubmit() {
    this.storeService.addAccount(this.form).subscribe(
      data=>{
        this.message = data["message"];
      }
    );
  }
}
