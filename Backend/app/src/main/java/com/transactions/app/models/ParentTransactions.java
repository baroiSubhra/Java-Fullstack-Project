package com.transactions.app.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParentTransactions extends ParentData {
  private Long totalPaidAmount;

  public void setParentTransaction(ParentData parentTransaction) {
    this.setId(parentTransaction.getId());
    this.setReceiver(parentTransaction.getReceiver());
    this.setSender(parentTransaction.getSender());
    this.setTotalAmount(parentTransaction.getTotalAmount());
  }
}
