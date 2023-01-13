package com.practice.web.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class EntityBase implements Serializable {

    private String active;
    private String addedBy;
    private Date addDate;
    private String changedBy;
    private Date changeDate;
    private boolean isActive;

    @Transient
    public boolean isActive() {
        return "Y".equals(this.active);
    }

    @Basic
    @Column(name = "active", nullable = false)
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Basic
    @Column(name = "add_by", nullable = false)
    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    @Basic
    @Column(name = "add_dtm", nullable = true)
    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    @Basic
    @Column(name = "chg_by", nullable = true)
    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    @Basic
    @Column(name = "chg_dtm", nullable = true)
    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

}
