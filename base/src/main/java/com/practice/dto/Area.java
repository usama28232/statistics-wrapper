package com.practice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Area {

    private String key;
    private String title;
    private String uuid;
    private Statistics statistics;

    private CompletableFuture<List<Statistic>> listCompletableFuture;

    @JsonProperty("statistics")
    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonIgnore
    public CompletableFuture<List<Statistic>> getListCompletableFuture() {
        return listCompletableFuture;
    }

    public void setListCompletableFuture(CompletableFuture<List<Statistic>> listCompletableFuture) {
        this.listCompletableFuture = listCompletableFuture;
    }

}
