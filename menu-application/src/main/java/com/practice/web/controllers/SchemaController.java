package com.practice.web.controllers;

import com.practice.dto.Schema;
import com.practice.web.services.CacheService;
import com.practice.web.services.SchemaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schema")
public class SchemaController {

    private SchemaService service;
    private CacheService cacheService;

    public SchemaController(SchemaService service, CacheService cacheService) {
        this.service = service;
        this.cacheService = cacheService;
    }

    @GetMapping("/cache/clear")
    public boolean clearCache(@RequestParam(name = "name", required = false) String cacheName) {
        return this.cacheService.clearCacheByName(cacheName);
    }

    @GetMapping("/get/{org}/{key}")
    public Schema getPortalAPI(@PathVariable String org, @PathVariable String key) {
        return this.service.getStatsForSchema(org, key);
    }

}