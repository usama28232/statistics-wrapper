package com.practice.web.entities;

import com.practice.web.entities.keys.MenuPK;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(MenuPK.class)
@Table(name = "menu")
public class Menu extends EntityBase implements Serializable {

    private String org;
    private String request;

    public Menu() {
    }

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
        Menu menu = (Menu) o;
        return Objects.equals(org, menu.org) && Objects.equals(request, menu.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(org, request);
    }
}
