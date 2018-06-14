package com.purdynet.faviconapp.service;

public interface FaviconService {
    String faviconUrl(final String url, final boolean getFresh);
    void refreshFaviconUrls(final Integer limit);
}
