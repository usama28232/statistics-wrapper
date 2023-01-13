package com.practice.web.entities;

import com.practice.web.entities.keys.MenuDetailPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(MenuDetailPK.class)
@Table(name = "menu_detail")
public class MenuDetail extends EntityBase implements Serializable {

    private String org;
    private String request;
    private String lang;
    private String text;
    private String title;

    public MenuDetail() {
    }

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
    @Column(name = "lang")
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
