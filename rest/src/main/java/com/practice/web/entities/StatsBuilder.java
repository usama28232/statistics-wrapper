package com.practice.web.entities;

import com.practice.web.entities.keys.MenuDetailPK;
import com.practice.web.entities.keys.StatsBuilderPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(StatsBuilderPK.class)
@Table(name = "stats_builder")
public class StatsBuilder extends EntityBase implements Serializable {

    private String org;
    private String view;
    private String description;
    private String aggregateField;
    private String aggregate_func;
    private String custom_agg;
    private String filter;
    private int maxRows;
    private String selection;
    private String userId;

    private String query;

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

    @Basic
    @Column(name = "aggregate_field")
    public String getAggregateField() {
        return aggregateField;
    }

    public void setAggregateField(String aggregateField) {
        this.aggregateField = aggregateField;
    }

    @Basic
    @Column(name = "aggregate_func")
    public String getAggregate_func() {
        return aggregate_func;
    }

    public void setAggregate_func(String aggregate_func) {
        this.aggregate_func = aggregate_func;
    }

    @Basic
    @Column(name = "custom_agg")
    public String getCustom_agg() {
        return custom_agg;
    }

    public void setCustom_agg(String custom_agg) {
        this.custom_agg = custom_agg;
    }

    @Basic
    @Column(name = "filter")
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Basic
    @Column(name = "max_rows")
    public int getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    @Basic
    @Column(name = "selection")
    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Transient
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
