package com.purdynet.faviconapp.dao.repos;

import com.purdynet.faviconapp.model.FaviconRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FaviconRecordMongoRepo extends MongoRepository<FaviconRecord, String> {}
