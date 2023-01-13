package com.practice.web.entities.keys;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StatsBuilderPK implements Serializable {

    private String org;
    private String view;

    @Id
    @Column(name = "s_org", nullable = false)
    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    @Id
    @Column(name = "s_view", nullable = false)
    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatsBuilderPK that = (StatsBuilderPK) o;
        return Objects.equals(org, that.org) && Objects.equals(view, that.view);
    }

    @Override
    public int hashCode() {
        return Objects.hash(org, view);
    }
}
