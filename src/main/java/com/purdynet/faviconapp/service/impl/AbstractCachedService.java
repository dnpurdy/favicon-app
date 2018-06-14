package com.purdynet.faviconapp.service.impl;

import org.ehcache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public abstract class AbstractCachedService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public <K,T> T getFromCache(Cache<K,T> cache, K key, Function<K,T> refreshFunc) {
        T cachedValue = cache.get(key);
        if (cachedValue == null) {
            logger.debug(String.format("Value for key [%s] not found in cache!  refreshing...", key.toString()));
            T newValue = refreshFunc.apply(key);
            cache.put(key,newValue);
            return newValue;
        } else {
            logger.debug(String.format("Value for key [%s] found in cache...", key.toString()));
            return cachedValue;
        }
    }
}
