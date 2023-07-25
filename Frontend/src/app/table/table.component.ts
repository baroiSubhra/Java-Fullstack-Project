import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent {
  @Input('tableData') tableData: any[] = [];
  @Input('headers') headers: string[] = [];
  @Input('tableKeys') tableKeys: string[] = [];
  @Input('loading') loading: boolean = false;
  @Output() rowClicked: EventEmitter<any> = new EventEmitter();

  tableClicked(item: any) {
    this.rowClicked.emit(item.id)
  }
}
