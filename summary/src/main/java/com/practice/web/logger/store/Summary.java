package com.practice.web.logger.store;

import com.practice.dto.LogSummaryData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class Summary {

    private static final Logger logger = LoggerFactory.getLogger(Summary.class);
    private static ConcurrentHashMap<String, LogSummaryData> dataConcurrentHashMap = new ConcurrentHashMap<>();
    private static String SUMMARY_MSG = "IN: {} OUT: {} \r\nTIME: {}ms K: {}\r\nQ: {}";

    private Summary() {
    }

    /**
     * Save Summary Object for Details
     *
     * @param key  identifier
     * @param data against identifier
     */
    public static void save(String key, LogSummaryData data) {
        dataConcurrentHashMap.put(key, data);
    }

    /**
     * Get Detail Summary by Key
     *
     * @param key identifier
     * @return Summary Data
     */
    public static LogSummaryData getByKey(String key) {
        if (dataConcurrentHashMap.containsKey(key)) {
            return dataConcurrentHashMap.get(key);
        }
        return null;
    }

    /**
     * Remove Data by Key
     *
     * @param key
     */
    public static void removeByKey(String key) {
        dataConcurrentHashMap.remove(key);
    }

    /**
     * Print Collected Summaries ordered by Duration Asc
     */
    public static void printSummary() {
        dataConcurrentHashMap.values()
                .stream()
                .sorted((o1, o2) -> o1.compare(o1, o2))
                .forEach(sd -> {
                    logger.error(
                            SUMMARY_MSG,
                            sd.getStartTime(),
                            sd.getEndTime(),
                            sd.getDuration(),
                            sd.getKey(),
                            sd.getValue()
                    );
                });
    }

}
