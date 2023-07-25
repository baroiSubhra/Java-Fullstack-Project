import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChildTransactionsComponent } from './child-transactions/child-transactions.component';
import { ParentTransactionsComponent } from './parent-transactions/parent-transactions.component';

const routes: Routes = [{ path: '', component: ParentTransactionsComponent }, { path: 'child/:id', component: ChildTransactionsComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
