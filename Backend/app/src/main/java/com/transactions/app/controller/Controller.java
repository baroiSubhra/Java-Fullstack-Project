package com.transactions.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.transactions.app.models.ChildTransactions;
import com.transactions.app.models.ParentTransactions;
import com.transactions.app.models.Response;
import com.transactions.app.service.TransactionService;

@CrossOrigin
@RestController
public class Controller {

  @Autowired
  private TransactionService transactionService;

  @GetMapping(value = "/ping")
  public String healthCheck() {
    return "pong";
  }

  @GetMapping(value = "/getParentTransactions/{pageSize}/{offset}")
  public Response<ParentTransactions> getParentTransactions(@PathVariable int pageSize,
      @PathVariable int offset) {
    return transactionService.getParentTransactions(pageSize, offset);
  }

  @GetMapping(value = "/getChildTransactions/{parentId}/{pageSize}/{offset}")
  public Response<ChildTransactions> getChildTransactions(@PathVariable int parentId, @PathVariable int pageSize,
      @PathVariable int offset) {
    return transactionService.getChildTransactions(parentId, pageSize, offset);
  }
}
