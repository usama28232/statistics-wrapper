package com.practice.web.controllers;

import com.practice.dto.Schema;
import com.practice.dto.Statistic;
import com.practice.web.services.CacheService;
import com.practice.web.services.StatsWrapperService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private StatsWrapperService service;
    private CacheService cacheService;

    public StatisticsController(StatsWrapperService service, CacheService cacheService) {
        this.service = service;
        this.cacheService = cacheService;
    }

    @GetMapping("/cache/clear")
    public boolean clearCache(@RequestParam(name = "name", required = false) String cacheName) {
        return this.cacheService.clearCacheByName(cacheName);
    }

    @GetMapping("/get/{org}/{request}/{view}/{user}")
    public List<Statistic> getStats(@PathVariable String org, @PathVariable String request, @PathVariable String view,
                                        @PathVariable String user) {
        try {
            return this.service.getStatsListForSchema(org, request, view, user);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
