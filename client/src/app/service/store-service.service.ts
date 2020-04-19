import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Game} from '../model/game';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Account} from '../model/account';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

// tslint:disable-next-line:variable-name
const _url = 'http://localhost:8080/';

@Injectable()
export class StoreService {

  private storesUrl: string;
  private storeUrl: string;
  private  accountUrl: string;
  private bagUrl: string;
  private addToCartUrl: string;
  private removeFromBagUrl: string;
  private checkoutUrl: string;
  private buyUrl: string;
  private addAccUrl: string;
  private successUrl: string;
  private purchasesUrl: string;
  private removeAccountUrl: string;

  constructor(private http: HttpClient) {
    this.storesUrl = _url + 'gamestores';
    this.storeUrl = _url + 'gamestore';
    this.accountUrl = _url + 'gamestore/account';
    this.bagUrl = _url + 'bag';
    this.addToCartUrl = _url + 'addtocart';
    this.removeFromBagUrl = _url + 'bag/removePurchase';
    this.checkoutUrl = _url + 'checkout';
    this.buyUrl = _url + 'pay';
    this.addAccUrl = _url + 'admin/addaccount';
    this.successUrl = _url + 'pay/success';
    this.purchasesUrl = _url + 'user/purchases';
    this.removeAccountUrl = _url + 'admin/removeAccount';
  }

  public findAllStores(): Observable<Game[]> {
    return this.http.get<Game[]>(this.storesUrl);
  }

  public findAcc(id: string, page: number, size: number): Observable<any> {
    const params = new HttpParams()
      .set('id', id.toString())
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<any>(this.storeUrl, {params});
  }

  public findAccWithParams(id: string, from: string, to: string, sort: string, page: number, size: number): Observable<any> {
    let params = new HttpParams()
      .set('id', id)
      .set('page', page.toString())
      .set('size', size.toString());
    if (from) {
      params = params.append('from', from);
    }
    if (to) {
      params = params.append('to', to);
    }
    if (sort) {
       params = params.append('sort', sort);
    }
    return this.http.get<any>(this.storeUrl, {params});
  }

  public findAccount(id: string): Observable<Account> {
    const params = new HttpParams()
      .set('id', id.toString());
    return this.http.get<Account>(this.accountUrl, {params});
  }

  getbag(id: number): Observable<any> {
    const  params = new HttpParams()
      .set('id', id.toString());
    return this.http.get<any>(this.bagUrl, {params});
  }

  addtocart(id: number, userId: number): Observable<any> {
    const  params = new HttpParams()
      .set('id', id.toString())
      .set('userId', userId.toString());
    return this.http.get<any>(this.addToCartUrl, {params});
  }

  deletefrombag(id: number): Observable<string> {
    const params = new HttpParams()
      .set('id', id.toString());
    return this.http.get<string>(this.removeFromBagUrl, {params});
  }


  checkout(): Observable<any> {
    return this.http.get<any>(this.checkoutUrl);
  }

  buy(sum: any ) {
    let paypalUrl: string;
    const params = new HttpParams()
      .set('sum', sum.toString());
    this.http.get<string>(this.buyUrl, {params}).subscribe(data => {
      paypalUrl = data['url'];
      window.location.href = paypalUrl.toString();
      }
    );
  }

  addAccount(form: any, gameId: any): Observable<any> {
    return this.http.post(this.addAccUrl, {
      gameId,
      description: form.description,
      login: form.login,
      password: form.password,
      price: form.price
    }, httpOptions);
  }

  successBuy(paymentid: any, payerid: any, accountsId: any[], id: number): Observable<any> {
    return this.http.post<any>(this.successUrl, {paymentId: paymentid,
      payerId: payerid.toString(), accounts: accountsId, userId: id});
  }

  findPurchases(id: any): Observable<any> {
    const params = new HttpParams()
      .set('id', id);
    return this.http.get<any>(this.purchasesUrl, {params});

  }

  deleteAccount(id: number): Observable<any> {
    const params = new HttpParams()
      .set('accountId', id.toString());
    return this.http.get<any>(this.removeAccountUrl, {params});
  }
}
