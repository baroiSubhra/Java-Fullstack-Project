import { Component } from '@angular/core';
import { TransactionService } from '../service/transaction.service';
import { environment } from 'src/environments/environment';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-parent-transactions',
  templateUrl: './parent-transactions.component.html',
  styleUrls: ['./parent-transactions.component.scss']
})
export class ParentTransactionsComponent {
  public tableData: any[] = []
  public headers: string[] = ["Id", "Sender", "Receiver", "Total Amount", "Total Paid Amount"]
  public tableKeys: string[] = ["id", "sender", "receiver", "totalAmount", "totalPaidAmount"]
  public pageNo: number = 0
  public totalPages: number = 0
  public loading: boolean = false
  constructor(private transactionService: TransactionService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.loading = true
    this.route.queryParams.subscribe(params => {
      this.pageNo = Number(params['pageNo']) || 0
      this.getTransactions()
    });
  }

  getTransactions() {
    this.transactionService.getParentTransactions(this.pageNo).subscribe(el => {
      this.tableData = el.data
      this.setPaginationData(el)
      this.loading = false
    })
  }

  setPaginationData(data: any) {
    this.pageNo = data.offset
    this.totalPages = Math.ceil(data.totalEntries / environment.PAGE_SIZE) - 1
  }

  handleRowClick(parentId: number) {
    this.router.navigate([`/child/${parentId}`])
  }

  changePageNo(pageNo: number) {
    this.router.navigate([], { queryParams: { pageNo: pageNo } })
  }
}
