package com.practice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

public class Statistic implements Cloneable, Comparable<Statistic> {

    private String viewNm;
    private String viewDesc;
    private int sort;
    private BigDecimal result;
    private String query;

    private CompletableFuture<BigDecimal> futureResult;

    public Statistic(String viewNm, String viewDesc, int sort, BigDecimal result, String query) {
        this.viewNm = viewNm;
        this.viewDesc = viewDesc;
        this.sort = sort;
        this.result = result;
        this.query = query;
    }

    @JsonProperty("view_name")
    public String getViewNm() {
        return viewNm;
    }

    public void setViewNm(String viewNm) {
        this.viewNm = viewNm;
    }

    @JsonProperty("view_description")
    public String getViewDesc() {
        return viewDesc;
    }

    public void setViewDesc(String viewDesc) {
        this.viewDesc = viewDesc;
    }

    @JsonProperty("sort")
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @JsonProperty("result")
    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    @JsonProperty("query")
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @JsonIgnore
    public CompletableFuture<BigDecimal> getFutureResult() {
        return futureResult;
    }

    public void setFutureResult(CompletableFuture<BigDecimal> futureResult) {
        this.futureResult = futureResult;
    }

    @Override
    public int compareTo(Statistic o1) {
        int ret = 0;
        if (this.sort < o1.sort) {
            ret = -1;
        } else if (this.sort > o1.sort) {
            ret = 1;
        }
        return ret;
    }
}
