package com.scheduler.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MyCacheManager implements CacheManager {

    ConcurrentMap<String, Cache> map = new ConcurrentHashMap<>(16);

    public MyCacheManager(String... cacheNames) {
        if (cacheNames != null) {
            for (String name : cacheNames) {
                this.map.put(name, new ConcurrentMapCache(name));
            }
        }
    }

    @Override
    public Cache getCache(String name) {
        return map.get(name);
    }

    @Override
    public Collection<String> getCacheNames() {
        return map.keySet();
    }
}
