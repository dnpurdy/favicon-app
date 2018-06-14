package com.purdynet.faviconapp.dao.impl;

import org.ehcache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class AbstractCacheDAO {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public <K,T> Optional<T> getFromCache(Cache<K,T> cache, K key, Function<K,Optional<T>> refreshFunc) {
        T cachedValue = cache.get(key);
        if (cachedValue == null) {
            logger.debug(String.format("Value for key [%s] not found in cache!  refreshing...", key.toString()));
            Optional<T> newValue = refreshFunc.apply(key);
            newValue.ifPresent(nv -> cache.put(key,nv));
            return newValue;
        } else {
            logger.debug(String.format("Value for key [%s] found in cache...", key.toString()));
            return Optional.of(cachedValue);
        }
    }

    public <K,R,T> T save(Cache<K,R> cache, R record, Supplier<K> keyFunc, Function<R,T> saveFunc) {
        T ret = saveFunc.apply(record);
        cache.put(keyFunc.get(), record);
        return ret;
    }
}
