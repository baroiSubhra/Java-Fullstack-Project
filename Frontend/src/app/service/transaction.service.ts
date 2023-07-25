import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private http: HttpClient) { }

  getParentTransactions(offset: number) {
    const url: string = environment.API_ENDPOINT + `/getParentTransactions/${environment.PAGE_SIZE}/${offset}`
    return this.http.get<any>(url);
  }

  getChildTransactions(parentId: number, offset: number) {
    const url: string = environment.API_ENDPOINT + `/getChildTransactions/${parentId}/${environment.PAGE_SIZE}/${offset}`
    return this.http.get<any>(url);
  }
}
