package com.practice.web.services;

import com.practice.web.exceptions.DomainException;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CacheService {

    private final static String CACHE_NOT_INIT_MSG = "app.cache.not.initialized";
    private final CacheManager cacheManager;

    @PersistenceContext
    private EntityManager entityManager;

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * Clear Hibernate First Level Cache
     *
     * @return true
     */
    public boolean clearSessionCache() {
        this.entityManager.clear();
        return true;
    }

    /**
     * Get All Stored Keys
     *
     * @return all cached keys now
     */
    public Map<String, List<String>> getAllCacheKeys() {
        Map<String, List<String>> cachedKeys = new HashMap<>();
        this.cacheManager.getCacheNames().forEach(cacheNm -> {
            List<String> keys = new ArrayList<>();
            Cache cache = this.getCache(cacheNm);
            cache.forEach(k -> {
                keys.add(
                        ((Cache.Entry) k).getKey().toString()
                );
            });
            cachedKeys.put(cacheNm, keys);
        });
        return cachedKeys;
    }

    /**
     * Check if this Cache Collection contains an entry
     *
     * @param cache previously initialized cache
     * @param key   to check if this cache contains key
     * @return true if key is found else false
     */
    public boolean containsKey(Cache cache, String key) {
        return cache.containsKey(key);
    }

    /**
     * Save value in the cache against provided key
     *
     * @param cache previously initialized cache
     * @param key   to save value against
     * @param value to store for future usage
     * @return true after saving value
     */
    public boolean putCache(Cache cache, String key, Object value) {
        cache.put(key, value);
        return true;
    }

    /**
     * Get Pre-Initialized Cache Object
     *
     * @param key Cache Identifier
     * @return Initialized Cache Object
     * @throws DomainException
     */
    public Cache getCache(String key) throws DomainException {
        Cache cache;
        cache = this.cacheManager.getCache(key);
        if (cache == null) {
            throw new DomainException(CACHE_NOT_INIT_MSG, key);
        }
        return cache;
    }

    /**
     * Get stored cache value
     *
     * @param cache pre-initialized cache object
     * @param key   value identifier
     * @return stored cache value
     */
    public Object getCacheValue(Cache cache, String key) {
        return cache.get(key);
    }

    /**
     * Remove specific cache
     *
     * @param cacheNm Pre-Initialized Cache Name
     * @return true after completion
     */
    @SuppressWarnings("rawtypes")
    public boolean clearCacheByName(String cacheNm) {
        if (cacheNm == null)
            return this.clearAllCache();
        Cache cache = this.getCache(cacheNm);
        if (cache != null) {
            cache.removeAll();
        }
        return true;
    }

    /**
     * Remove All Cached Values
     *
     * @return true after completion
     */
    public boolean clearAllCache() {
        this.cacheManager.getCacheNames()
                .forEach(name -> this.cacheManager.getCache(name).removeAll());
        return true;
    }

}
