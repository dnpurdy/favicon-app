package com.purdynet.faviconapp.service.impl;

import com.purdynet.faviconapp.model.FaviconRecord;
import com.purdynet.faviconapp.service.CacheService;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.ExpiryPolicy;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.Supplier;

@Service
public class CacheServiceImpl implements CacheService {
    private CacheManager cacheManager;
    private Cache<String, FaviconRecord> faviconRecordCache;

    public CacheServiceImpl() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        cacheManager.createCache("faviconRecordCache", CacheConfigurationBuilder.newCacheConfigurationBuilder(
                String.class, FaviconRecord.class,
                ResourcePoolsBuilder.heap(10))
                        .withExpiry(new ExpiryPolicy<String, FaviconRecord>() {
                            @Override
                            public Duration getExpiryForCreation(String key, FaviconRecord value) {
                                return Duration.ofSeconds(3600);
                            }

                            @Override
                            public Duration getExpiryForAccess(String key, Supplier<? extends FaviconRecord> value) {
                                return null;
                            }

                            @Override
                            public Duration getExpiryForUpdate(String key, Supplier<? extends FaviconRecord> oldValue, FaviconRecord newValue) {
                                return null;
                            }
                        }).build());
    }

    @Override
    public Cache<String, FaviconRecord> getFaviconRecordCache() {
        return cacheManager.getCache("faviconRecordCache", String.class, FaviconRecord.class);
    }
}