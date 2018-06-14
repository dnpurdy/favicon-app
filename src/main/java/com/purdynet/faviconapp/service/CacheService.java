package com.purdynet.faviconapp.service;

import com.purdynet.faviconapp.model.FaviconRecord;
import org.ehcache.Cache;

import java.util.Optional;

public interface CacheService {
    Cache<String, FaviconRecord> getFaviconRecordCache();
}
