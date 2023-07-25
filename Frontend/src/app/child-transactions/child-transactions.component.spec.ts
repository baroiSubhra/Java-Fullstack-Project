import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChildTransactionsComponent } from './child-transactions.component';

describe('ChildTransactionsComponent', () => {
  let component: ChildTransactionsComponent;
  let fixture: ComponentFixture<ChildTransactionsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChildTransactionsComponent]
    });
    fixture = TestBed.createComponent(ChildTransactionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
