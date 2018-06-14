package com.purdynet.faviconapp.service.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class FaviconServiceImplTest {

    @Test
    public void cleanseUrl() {
        assertEquals("http://www.google.com", FaviconServiceImpl.cleanseUrl("google.com"));
        assertEquals("http://www.google.com", FaviconServiceImpl.cleanseUrl("www.google.com"));
        assertEquals("http://www.google.com", FaviconServiceImpl.cleanseUrl("http://www.google.com"));
        assertEquals("https://www.google.com", FaviconServiceImpl.cleanseUrl("https://www.google.com"));

        assertEquals("http://www.wikipedia.org", FaviconServiceImpl.cleanseUrl("wikipedia.org"));
        assertEquals("http://www.wikipedia.org", FaviconServiceImpl.cleanseUrl("www.wikipedia.org"));
        assertEquals("http://www.wikipedia.org", FaviconServiceImpl.cleanseUrl("http://www.wikipedia.org"));
        assertEquals("https://www.wikipedia.org", FaviconServiceImpl.cleanseUrl("https://www.wikipedia.org"));
    }
}