package com.practice.dto;

import java.util.Comparator;

public class LogSummaryData implements Comparator<LogSummaryData> {
    private long startTime;
    private long endTime;
    private long duration;
    private String key;
    private String value;

    public LogSummaryData(long startTime) {
        this.startTime = startTime;
    }

    public LogSummaryData(long startTime, long endTime, long duration, String key, String value) {
        this(startTime);
        this.endTime = endTime;
        this.duration = duration;
        this.key = key;
        this.value = value;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compare(LogSummaryData o1, LogSummaryData o2) {
        int ret = 0;
        if (o1.duration < o2.duration) {
            ret = -1;
        } else if (o1.duration > o2.duration) {
            ret = 1;
        }
        return ret;
    }
}
