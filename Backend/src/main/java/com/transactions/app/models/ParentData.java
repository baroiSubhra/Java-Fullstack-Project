package com.transactions.app.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParentData {
  private Integer id;
  private String sender;
  private String receiver;
  private Long totalAmount;
}
