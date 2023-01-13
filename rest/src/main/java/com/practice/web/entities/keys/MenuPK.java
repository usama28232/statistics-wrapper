package com.practice.web.entities.keys;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MenuPK implements Serializable {

    private String org;
    private String request;

    @Id
    @Column(name = "org", nullable = false)
    public String getOrg() {
        return org;
    }
    public void setOrg(String org) {
        this.org = org;
    }

    @Id
    @Column(name = "request", nullable = false)
    public String getRequest() {
        return request;
    }
    public void setRequest(String request) {
        this.request = request;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuPK that = (MenuPK) o;
        return Objects.equals(org, that.org) && Objects.equals(request, that.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(org, request);
    }
}
