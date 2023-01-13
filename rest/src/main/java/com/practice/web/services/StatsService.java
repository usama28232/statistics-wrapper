package com.practice.web.services;

import com.practice.annotations.Asynchronus;
import com.practice.constants.AppConstants;
import com.practice.dto.Statistic;
import com.practice.enums.CacheKeys;
import com.practice.web.engine.QueryExtractor;
import com.practice.web.entities.MenuStatsMapping;
import com.practice.web.entities.StatsBuilder;
import com.practice.web.entities.keys.StatsBuilderPK;
import com.practice.web.exceptions.DomainException;
import com.practice.web.repositories.StatsBuilderRepository;
import com.practice.web.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.cache.Cache;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class StatsService extends ServiceBase<StatsBuilder, StatsBuilderPK> {

    private DataService dataService;
    private StatsBuilderRepository statsBuilderRepository;
    private CacheService cacheService;

    public StatsService(StatsBuilderRepository statsBuilderRepository, CacheService cacheService, DataService dataService) {
        super(statsBuilderRepository);
        this.cacheService = cacheService;
        this.dataService = dataService;
        this.statsBuilderRepository = statsBuilderRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(StatsService.class);

    @Asynchronus
    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = DomainException.class)
    public CompletableFuture<List<Statistic>> getStatsDataAsync(MenuStatsMapping menuStatsMapping, String userId) {
        List<Statistic> response = this.getStatsData(
                        menuStatsMapping.getsOrg(), menuStatsMapping.getsView(), userId)
                .stream().map(item -> {
                            CompletableFuture<BigDecimal> result = dataService.getSelectResult(
                                    menuStatsMapping.getOrg(),
                                    menuStatsMapping.getRequest(),
                                    item.getView(),
                                    item.getQuery()
                            );
                            Statistic node = new Statistic(
                                    item.getView(),
                                    item.getView(),
                                    0,
                                    new BigDecimal(0),
                                    item.getQuery()
                            );

                            node.setFutureResult(result);
                            return node;
                        }
                ).collect(Collectors.toList());

        List<CompletableFuture<BigDecimal>> futures = response.stream()
                .map(node -> node.getFutureResult()).collect(Collectors.toList());
        CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[futures.size()])
        ).join();
        return CompletableFuture.completedFuture(
                response.stream()
                        .sorted((Statistic::compareTo))
                        .map(entity -> {
                            try {
                                entity.setResult(
                                        entity.getFutureResult().get()
                                );
                            } catch (Exception e) {
                                logger.error(e.getMessage());
                                e.printStackTrace();
                            }
                            return entity;
                        }).collect(Collectors.toList())
        );
    }

    private List<StatsBuilder> getStatsData(String org, String view, String userId) {
        List<StatsBuilder> out = null;
        String key = Utils.keyMaker(org, view);
        Cache cache = this.cacheService.getCache(CacheKeys.STATISTICS.value);
        if (this.cacheService.containsKey(cache, key)) {
            out = (List<StatsBuilder>) this.cacheService.getCacheValue(cache, key);
        } else {
            out = (List<StatsBuilder>) this.statsBuilderRepository.
                    findAllByOrgAndViewAndUserIdAndActive(org, view, userId, AppConstants.FLAG_Y).stream()
                    .map(entity -> {
                        entity.setQuery(
                                new QueryExtractor(entity).build()
                        );
                        return entity;
                    }).collect(Collectors.toList());
        }
        return out;
    }


}
