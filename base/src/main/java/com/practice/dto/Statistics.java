package com.practice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.Collection;

public class Statistics {

    private Collection<Statistic> statistic;

    public Statistics() {
    }

    public Statistics(Collection<Statistic> statistic) {
        this.statistic = statistic;
    }

    @JsonProperty("statistic")
    @JacksonXmlElementWrapper(useWrapping = false)
    public Collection<Statistic> getStatistic() {
        return statistic;
    }

    public void setStatistic(Collection<Statistic> statistic) {
        this.statistic = statistic;
    }
}
