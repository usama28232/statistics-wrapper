package com.practice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Schema {
    private String org;
    private String key;
    private String title;
    private String desc;
    private Map paramlist;
    private Areas areas;

    @JsonProperty("org")
    public String getOrg() {
        return org;
    }
    public void setOrg(String org) {
        this.org = org;
    }

    @JsonProperty("key")
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JsonProperty("params")
    public Map getParamlist() {
        return paramlist;
    }
    public void setParamlist(Map paramlist) {
        this.paramlist = paramlist;
    }

    @JsonProperty("areas")
    public Areas getAreas() {
        return areas;
    }
    public void setAreas(Areas areas) {
        this.areas = areas;
    }
}
