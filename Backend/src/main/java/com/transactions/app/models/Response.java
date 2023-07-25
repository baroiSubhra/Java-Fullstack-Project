package com.transactions.app.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Response<E> {
    private Integer status;
    private Integer code;
    private String message;
    private List<E> data;
    private Integer totalEntries;
    private Integer pageSize;
    private Integer offset;

    public void setPaginationData(int totalEntries, int pageSize, int currentOffset) {
        this.setTotalEntries(totalEntries);
        this.setPageSize(pageSize);
        this.setOffset(currentOffset);
    }
}
