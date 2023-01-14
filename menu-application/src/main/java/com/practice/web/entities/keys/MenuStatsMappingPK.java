package com.practice.web.entities.keys;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MenuStatsMappingPK implements Serializable {
    private String org;
    private String request;
    private String sOrg;
    private String sView;

    @Id
    @Column(name = "org")
    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    @Id
    @Column(name = "request")
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Id
    @Column(name = "s_org")
    public String getsOrg() {
        return sOrg;
    }

    public void setsOrg(String sOrg) {
        this.sOrg = sOrg;
    }

    @Id
    @Column(name = "s_view")
    public String getsView() {
        return sView;
    }

    public void setsView(String sView) {
        this.sView = sView;
    }
}
