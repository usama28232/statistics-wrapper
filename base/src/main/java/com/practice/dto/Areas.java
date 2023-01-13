package com.practice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.Collection;

public class Areas {

    private Collection<Area> areas;

    public Areas() {
    }

    public Areas(Collection<Area> areas) {
        this.areas = areas;
    }

    @JsonProperty("area")
    @JacksonXmlElementWrapper(useWrapping = false)
    public Collection<Area> getAreas() {
        return areas;
    }

    public void setAreas(Collection<Area> areas) {
        this.areas = areas;
    }
}
