import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { TransactionService } from '../service/transaction.service';
import { combineLatest, map } from 'rxjs';

@Component({
  selector: 'app-child-transactions',
  templateUrl: './child-transactions.component.html',
  styleUrls: ['./child-transactions.component.scss']
})
export class ChildTransactionsComponent implements OnInit {
  public parentId: number = 0;
  public tableData: any[] = []
  public headers: string[] = ["Id", "Sender", "Receiver", "Total Amount", "Paid Amount"]
  public tableKeys: string[] = ["id", "sender", "receiver", "totalAmount", "paidAmount"]
  public pageNo: number = 0
  public totalPages: number = 0
  public loading: boolean = false
  constructor(private transactionService: TransactionService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.loading = true
    combineLatest([this.route.params, this.route.queryParams]).pipe(map(result => ({ parentId: result[0]['id'], pageNo: result[1]['pageNo'] }))).subscribe(result => {
      this.parentId = result.parentId
      this.pageNo = result.pageNo || 0
      this.getTransactions()
    })
  }

  setPaginationData(data: any) {
    this.pageNo = data.offset
    this.totalPages = Math.ceil(data.totalEntries / environment.PAGE_SIZE) - 1
  }

  getTransactions() {
    this.transactionService.getChildTransactions(this.parentId, this.pageNo).subscribe(el => {
      this.tableData = el.data
      this.setPaginationData(el)
      this.loading = false
    })
  }

  changePageNo(pageNo: number) {
    this.router.navigate([], { queryParams: { pageNo: pageNo } })
  }
}
