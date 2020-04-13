import { Component, OnInit } from '@angular/core';
import {Game} from '../../model/game';
import {StoreService} from '../../service/store-service.service';
import {TokenstorageService} from '../../service/tokenstorage.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent implements OnInit {

  stores: Game[];
  form: any = {};
  currentUser: any;
  querySubscription: Subscription;
  message: string;
  gameId: string;

  constructor(private router: Router, private route: ActivatedRoute, private storeService: StoreService, private token: TokenstorageService) {
    this.querySubscription = route.queryParams.subscribe(
      (queryParam: any) => {
        this.gameId = queryParam.gameId;

      }
    );
  }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    if (!this.currentUser.roles.includes('ROLE_ADMIN')) {
      this.router.navigateByUrl('/forbidden');
    } else {
      this.storeService.findAllStores().subscribe(data => {
        this.stores = data;
      });
    }
  }

  onSubmit() {
    this.storeService.addAccount(this.form, this.gameId).subscribe(
      data => {
        this.message = data.message;
        setTimeout(() => {
          this.router.navigate(['/gamestore'], {queryParams: {id: this.gameId}});
        }, 2000);
      }
    );
  }
}
