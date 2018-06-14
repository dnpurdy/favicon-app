package com.purdynet.faviconapp.dao;

import com.purdynet.faviconapp.model.FaviconRecord;

import java.util.Optional;

public interface FaviconDAO {
    Optional<FaviconRecord> getFaviconRecord(final String baseUrl);
    String putFaviconRecord(final FaviconRecord faviconRecord);
}
