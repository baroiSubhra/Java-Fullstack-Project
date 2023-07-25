import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentTransactionsComponent } from './parent-transactions.component';

describe('ParentTransactionsComponent', () => {
  let component: ParentTransactionsComponent;
  let fixture: ComponentFixture<ParentTransactionsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ParentTransactionsComponent]
    });
    fixture = TestBed.createComponent(ParentTransactionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
