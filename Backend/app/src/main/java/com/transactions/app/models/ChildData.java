package com.transactions.app.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChildData {
  private Integer id;
  private Integer parentId;
  private Long paidAmount;
}
