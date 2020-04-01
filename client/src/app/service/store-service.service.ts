import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Game} from "../model/game";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Account} from "../model/account";

@Injectable()
export class StoreService {
  private storesUrl: string;
  private storeUrl : string;
  private  accountUrl: string;
  private bagUrl: string;
  private addToCartUrl: string;
  private removeFromBagUrl: string;
  private checkoutUrl: string;
  private buyUrl: string;
  private addAccUrl: string;

  public findAll(): Observable<Game[]>{
    return this.http.get<Game[]>(this.storesUrl);
  }

  public find(id:string): Observable<any>{
    const params = new HttpParams()
      .set('id', id.toString());
    return this.http.get<any>(this.storeUrl,{params});
  }

  public findAccount(id:string): Observable<Account>{
    const params = new HttpParams()
      .set('id', id.toString());
    return this.http.get<Account>(this.accountUrl,{params});
  }


  constructor(private http: HttpClient) {
    this.storesUrl = 'http://localhost:8080/gamestores';
    this.storeUrl = 'http://localhost:8080/gamestore';
    this.accountUrl = 'http://localhost:8080/gamestore/account';
    this.bagUrl = 'http://localhost:8080/bag'
    this.addToCartUrl = 'http://localhost:8080/addtocart';
    this.removeFromBagUrl = 'http://localhost:8080/bag/removePurchase';
    this.checkoutUrl = 'http://localhost:8080/checkout';
    this.buyUrl = 'http://localhost:8080/pay';
    this.addAccUrl = 'http://localhost:8080/admin/addaccount';
  }

  getbag(): Observable<any>{
    return this.http.get<any>(this.bagUrl);
  }

  addtocart(id: number): Observable<any> {
    const  params = new HttpParams()
      .set('id', id.toString());
    return this.http.get<any>(this.addToCartUrl, {params});
  }

  deletefrombag(id: number): Observable<string>{
    const params = new HttpParams()
      .set('id',id.toString());
    return this.http.get<string>(this.removeFromBagUrl, {params});
  }


  checkout(): Observable<any>{
    return this.http.get<any>(this.checkoutUrl);
  }

  buy(sum: any ) {
    let url: String;
    const params = new HttpParams()
      .set('sum',sum.toString());
    this.http.get<String>(this.buyUrl, {params}).subscribe(data=>{
        url = data["url"];
        window.location.href =url.toString();
        // this.http.get(url.toString());
      }
    );

  }

  addAccount(form: any): Observable<any> {
    return this.http.post(this.addAccUrl, {
      game: form.game,
      description: form.description,
      login: form.login,
      password: form.password,
      price: form.price
    });
  }
}
