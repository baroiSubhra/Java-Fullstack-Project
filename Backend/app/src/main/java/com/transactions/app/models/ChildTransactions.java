package com.transactions.app.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChildTransactions {
  private Integer id;
  private String sender;
  private String receiver;
  private Long totalAmount;
  private Long paidAmount;

  public void setParentTransactionData(ParentData parentTransaction) {
    this.setSender(parentTransaction.getSender());
    this.setReceiver(parentTransaction.getReceiver());
    this.setTotalAmount(parentTransaction.getTotalAmount());
  }

  public void setChildTransactionData(ChildData childTransaction) {
    this.setId(childTransaction.getId());
    this.setPaidAmount(childTransaction.getPaidAmount());
  }
}
