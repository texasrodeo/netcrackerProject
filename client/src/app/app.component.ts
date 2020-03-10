import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',


  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;
  //param: {};

  constructor() {
    this.title = 'Магазин продажи аккаунтов';
   // this.param = {};
    //http.get('main').subscribe(data => this.param = data);
  }
}



