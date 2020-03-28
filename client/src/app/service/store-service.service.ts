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
  }

  getbag(id: any): Observable<Account[]>{
    const params = new HttpParams()
      .set('id', id)
    return this.http.get<Account[]>(this.accountUrl,{params});

  }

  addtocart(id: number): Observable<any> {
    const  params = new HttpParams()
      .set('id', id.toString());
    return this.http.get<any>(this.addToCartUrl, {params});
  }

  deletefrombag(id: number):Observable<any> {
    const params = new HttpParams()
      .set('id',id.toString());
    return  this.http.get<any>(this.removeFromBagUrl, {params});
  }
}
