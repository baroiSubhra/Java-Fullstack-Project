package com.transactions.app.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChildFile {
  private List<ChildData> data;
}
