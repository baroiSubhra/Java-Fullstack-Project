package com.transactions.app.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transactions.app.models.ChildFile;
import com.transactions.app.models.ChildData;
import com.transactions.app.models.ChildTransactions;
import com.transactions.app.models.ParentFile;
import com.transactions.app.models.ParentData;
import com.transactions.app.models.ParentTransactions;
import com.transactions.app.models.Response;

@Service
public class TransactionService {

  @Autowired
  private JsonFileService fileService;

  private final String parentFilePath = new File("src/main/resources/static/parent.json").toString();
  private final String childFilePath = new File("src/main/resources/static/child.json").toString();;

  public <T> void setResponseStatusAndMessageBasedOnCode(Response<T> rsp, int code, String msg) {
    switch (code) {
      case 200: {
        rsp.setStatus(200);
        rsp.setMessage("Ok");
        rsp.setCode(0);
        break;
      }
      case 201: {
        rsp.setStatus(201);
        rsp.setMessage("Added Succesfully");
        rsp.setCode(0);
        break;
      }
      case 500: {
        rsp.setStatus(500);
        rsp.setMessage("Server Error - " + msg);
        rsp.setCode(1);
        break;
      }
      default: {
        rsp.setStatus(400);
        rsp.setMessage("Error - " + msg);
        rsp.setCode(1);
      }
    }
  }

  public <T> void setResponseStatusAndMessageBasedOnCode(Response<T> rsp, int code) {
    this.setResponseStatusAndMessageBasedOnCode(rsp, code, "");
  }

  public Response<ParentTransactions> getParentTransactions(int pageSize, int offset) {
    Response<ParentTransactions> rsp = new Response<ParentTransactions>();
    List<ParentTransactions> requiredData = new ArrayList<ParentTransactions>();
    int totalEntries = 0;
    try {
      ParentFile parentFile = fileService.readJsonFile(
          parentFilePath,
          ParentFile.class);
      List<ParentData> parentDataList = parentFile.getData();
      totalEntries = parentDataList.size();
      List<Integer> splicingIndex = getStartAndEndIndex(totalEntries, pageSize, offset);
      parentDataList = parentDataList.subList(splicingIndex.get(0), splicingIndex.get(1));
      ChildFile childFile = fileService.readJsonFile(
          childFilePath,
          ChildFile.class);
      List<ChildData> childData = childFile.getData();
      for (ParentData parentData : parentDataList) {
        ParentTransactions parentTransaction = new ParentTransactions();
        parentTransaction.setParentTransaction(parentData);
        Long totalPaidAmount = (long) 0;
        childData = childData.stream()
            .filter(element -> element.getParentId() == parentTransaction.getId()).collect(Collectors.toList());
        for (ChildData childTransactions : childData) {
          totalPaidAmount = totalPaidAmount + childTransactions.getPaidAmount();
        }
        parentTransaction.setTotalPaidAmount(totalPaidAmount);
        requiredData.add(parentTransaction);
      }
      rsp.setData(requiredData);
      rsp.setPaginationData(totalEntries, pageSize, offset);
      this.setResponseStatusAndMessageBasedOnCode(rsp, 200);
    } catch (Exception e) {
      this.setResponseStatusAndMessageBasedOnCode(rsp, 500, e.toString());
      System.out.println(e);
    }
    return rsp;
  }

  public Response<ChildTransactions> getChildTransactions(int parentId, int pageSize, int offset) {
    Response<ChildTransactions> rsp = new Response<ChildTransactions>();
    List<ChildTransactions> requiredData = new ArrayList<ChildTransactions>();
    int totalEntries = 0;
    try {
      ParentFile parentFile = fileService.readJsonFile(
          parentFilePath,
          ParentFile.class);
      ParentData parentTransaction = parentFile.getData().stream().filter(element -> element.getId() == parentId)
          .findAny().orElse(null);
      if (parentTransaction != null) {
        ChildFile childFile = fileService.readJsonFile(
            childFilePath,
            ChildFile.class);
        List<ChildData> chidlDataList = childFile.getData().stream()
            .filter(element -> element.getParentId() == parentId)
            .collect(Collectors.toList());
        totalEntries = chidlDataList.size();
        List<Integer> splicingIndex = getStartAndEndIndex(totalEntries, pageSize, offset);
        chidlDataList = chidlDataList.subList(splicingIndex.get(0), splicingIndex.get(1));
        for (ChildData childData : chidlDataList) {
          ChildTransactions childTransaction = new ChildTransactions();
          childTransaction.setParentTransactionData(parentTransaction);
          childTransaction.setChildTransactionData(childData);
          requiredData.add(childTransaction);
        }
      }
      rsp.setData(requiredData);
      rsp.setPaginationData(totalEntries, pageSize, offset);
      this.setResponseStatusAndMessageBasedOnCode(rsp, 200);
    } catch (Exception e) {
      this.setResponseStatusAndMessageBasedOnCode(rsp, 500, e.toString());
      System.out.println(e);
    }
    return rsp;
  }

  private List<Integer> getStartAndEndIndex(int totalEntries, int pageSize, int offset) {
    List<Integer> indexList = new ArrayList<Integer>();
    int startSublistSpliceIndex = pageSize * offset;
    int endSublistSpliceIndex = pageSize * (offset + 1);
    if (startSublistSpliceIndex > totalEntries || offset < 0) {
      indexList.add(0);
      indexList.add(0);
      return indexList;
    }
    if (endSublistSpliceIndex > totalEntries) {
      endSublistSpliceIndex = totalEntries;
    }
    indexList.add(startSublistSpliceIndex);
    indexList.add(endSublistSpliceIndex);
    return indexList;
  }
}
