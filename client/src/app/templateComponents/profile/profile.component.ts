import { Component, OnInit } from '@angular/core';
import {TokenstorageService} from "../../service/tokenstorage.service";
import {AppService} from "../../service/app-service.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser: any;
  emailConfirmed = false;

  constructor(private token: TokenstorageService, private app:AppService) { }

  ngOnInit() {
    this.currentUser = this.token.getUser();
    this.app.checkEmail(this.currentUser.id).subscribe(
      data=>{

        this.emailConfirmed = data["res"];
      }
    )
  }



}
