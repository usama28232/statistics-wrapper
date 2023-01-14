package com.practice.web.services;

import com.practice.dto.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MenuServiceProxy {

    @Value("${app.menu.service.url}")
    private String serviceUrl;

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;


    public Schema getSchema(Schema request) {
        return restTemplate.getForObject(
                serviceUrl.concat("/schema/get/{string}/{string}"),
                Schema.class,
                request.getOrg(),
                request.getKey()
        );
    }

}