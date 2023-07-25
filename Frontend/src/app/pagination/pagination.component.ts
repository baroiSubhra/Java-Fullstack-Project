import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent {
  @Input('loading') loading: boolean = false;
  @Input('totalPages') totalPages: number = 0;
  @Input('pageNo') pageNo: number = 0;
  @Output() pageNoEmitter = new EventEmitter<number>();

  emitPageNo() {
    this.pageNoEmitter.emit(this.pageNo)
  }

  decrementPageNo() {
    if (this.pageNo > 0) this.pageNo--;
    this.emitPageNo()
  }

  incrementPageNo() {
    if (this.pageNo < this.totalPages) this.pageNo++;
    this.emitPageNo()
  }
  
  pageNoClicked(index: number) {
    this.pageNo = index;
    this.emitPageNo()
  }
}
