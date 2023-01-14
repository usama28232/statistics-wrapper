package com.practice.web.services;

import com.practice.dto.Statistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class StatsWrapperService {

    private StatsService statsService;
    private static final Logger logger = LoggerFactory.getLogger(StatsWrapperService.class);

    public StatsWrapperService(StatsService statsService) {
        this.statsService = statsService;
    }

    public List<Statistic> getStatsListForSchema(String org, String request, String view, String user) throws ExecutionException, InterruptedException {
        CompletableFuture<List<Statistic>> listCompletableFuture = this.statsService.getStatsDataAsync(
                org, request, view, user);
        return listCompletableFuture.get();
    }

}