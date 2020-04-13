import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PayresultComponent } from './payresult.component';

describe('PayresultComponent', () => {
  let component: PayresultComponent;
  let fixture: ComponentFixture<PayresultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PayresultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PayresultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
