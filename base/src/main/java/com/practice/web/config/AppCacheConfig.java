package com.practice.web.config;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.expiry.ExpiryPolicy;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.cache.CacheManager;
import javax.cache.Caching;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableCaching
public class AppCacheConfig {
    @Value("${app.cache.heap:2000}")
    private int heap;

    @Value("${app.cache.off-heap:100}")
    private int offHeap;

    @Value("${app.configured.cache.list:null}")
    private List<String> configuredCaches;

    @Bean
    public CacheManager appCacheManager() {
        EhcacheCachingProvider provider = (EhcacheCachingProvider) Caching.getCachingProvider();
        Map<String, CacheConfiguration<?, ?>> caches = new HashMap<>();
        if (this.configuredCaches != null && this.configuredCaches.size() > 0) {
            for (String cache : this.configuredCaches) {
                caches.put(cache, getPropBasicCache());
            }
        }
        DefaultConfiguration configuration = new DefaultConfiguration(caches, provider.getDefaultClassLoader());
        return new JCacheCacheManager(provider.getCacheManager(provider.getDefaultURI(), configuration)).getCacheManager();
    }

    private CacheConfiguration<?, ?> getPropBasicCache() {
        final ResourcePoolsBuilder res = ResourcePoolsBuilder
                .heap(this.heap)
                .offheap(this.offHeap, MemoryUnit.MB);
        // Spring does not allow anything else than Objects...
        final CacheConfigurationBuilder<Object, Object> newCacheConfigurationBuilder = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Object.class, Object.class, res)
                .withExpiry(ExpiryPolicy.NO_EXPIRY)
                .withDefaultDiskStoreThreadPool();
        return newCacheConfigurationBuilder.build();
    }
}
