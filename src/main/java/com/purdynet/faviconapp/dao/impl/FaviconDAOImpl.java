package com.purdynet.faviconapp.dao.impl;

import com.purdynet.faviconapp.dao.FaviconDAO;
import com.purdynet.faviconapp.dao.repos.FaviconRecordMongoRepo;
import com.purdynet.faviconapp.model.FaviconRecord;
import com.purdynet.faviconapp.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FaviconDAOImpl extends AbstractCacheDAO implements FaviconDAO {

    private final CacheService cacheService;
    private final FaviconRecordMongoRepo faviconRecordMongoRepo;

    @Autowired
    public FaviconDAOImpl(final CacheService cacheService, final FaviconRecordMongoRepo faviconRecordMongoRepo) {
        this.cacheService = cacheService;
        this.faviconRecordMongoRepo = faviconRecordMongoRepo;
    }

    @Override
    public Optional<FaviconRecord> getFaviconRecord(String baseUrl) {
        return getFromCache(cacheService.getFaviconRecordCache(), baseUrl, faviconRecordMongoRepo::findById);
    }

    @Override
    public String putFaviconRecord(FaviconRecord faviconRecord) {
        return save(cacheService.getFaviconRecordCache(), faviconRecord,
                faviconRecord::getBaseUrl,
                (x) -> { faviconRecordMongoRepo.save(x); return x.getBaseUrl(); }
                );
    }
}
