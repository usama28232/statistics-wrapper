package com.practice.web.services;

import com.practice.constants.AppConstants;
import com.practice.dto.Area;
import com.practice.dto.Areas;
import com.practice.dto.Statistic;
import com.practice.dto.Statistics;
import com.practice.enums.CacheKeys;
import com.practice.web.entities.MenuStatsMapping;
import com.practice.web.entities.keys.MenuStatsMappingPK;
import com.practice.web.repositories.MenuStatsMappingRepository;
import com.practice.web.utils.Utils;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class MenuStatsMappingService extends ServiceBase<MenuStatsMapping, MenuStatsMappingPK> {

    private CacheService cacheService;
    private StatsProxyService statsService;
    private MenuStatsMappingRepository repository;

    public MenuStatsMappingService(MenuStatsMappingRepository repository, StatsProxyService statsService, CacheService cacheService) {
        super(repository);
        this.repository = repository;
        this.statsService = statsService;
        this.cacheService = cacheService;
    }

    public Areas getAreas(String org, String req, String userId) {
        List<Area> areaList = new ArrayList<>();
        this.getMappings(org, req).stream()
                .forEach(e -> {
                    Area a = new Area();
                    a.setKey(e.getsView());
                    a.setTitle(req.concat(AppConstants.DELIM).concat(e.getsView()));
                    a.setStatistics(
                            new Statistics(
                                    this.statsService.getStats(org, req, e.getsView(), userId)
                            )
                    );
                    areaList.add(a);
                });


        return new Areas(areaList.stream()
                .filter(x -> !x.getStatistics().getStatistic().isEmpty()).collect(Collectors.toList()));
    }

    public List<MenuStatsMapping> getMappings(String org, String request) {
        List<MenuStatsMapping> out = null;
        String key = Utils.keyMaker(org, request);
        Cache cache = this.cacheService.getCache(CacheKeys.AREA.value);
        if (this.cacheService.containsKey(cache, key)) {
            out = (List<MenuStatsMapping>) this.cacheService.getCacheValue(cache, key);
        } else {
            out = (List<MenuStatsMapping>) this.repository.findAllByOrgAndRequestAndActive(org, request, AppConstants.FLAG_Y);
        }
        return out;
    }

}
