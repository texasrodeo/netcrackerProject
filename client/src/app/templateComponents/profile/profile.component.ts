import { Component, OnInit } from '@angular/core';
import {TokenstorageService} from "../../service/tokenstorage.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser: any;

  constructor(private token: TokenstorageService) { }

  ngOnInit() {
    this.currentUser = this.token.getUser();
  }

}
