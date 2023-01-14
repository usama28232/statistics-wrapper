package com.practice.web.services;

import com.practice.dto.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StatsProxyService {

    @Value("${app.stats.service.url}")
    private String serviceUrl;

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    public List<Statistic> getStats(String org, String key, String view, String user) {
        return restTemplate.getForObject(serviceUrl
                .concat("/statistics/get/{string}/{string}/{string}/{string}"), List.class, org, key, view, user);
    }

}
