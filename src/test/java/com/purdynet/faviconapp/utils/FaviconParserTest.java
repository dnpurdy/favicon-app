package com.purdynet.faviconapp.utils;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class FaviconParserTest {
    @Test
    public void parse_yahoo() throws Exception {
        String parse = FaviconParser.parse("http://www.yahoo.com", getTestHtml("samples/yahoo.com.html"));

        assertEquals("Not as expected!", "https://mbp.yimg.com/sy/rz/l/favicon.ico", parse);
    }

    @Test
    public void parse_amazon() throws Exception {
        String parse = FaviconParser.parse("http://www.amazon.com", getTestHtml("samples/amazon.com.html"));
        assertEquals("Not as expected!", null, parse);
    }

    @Test
    public void parse_cooley() throws Exception {
        String parse = FaviconParser.parse("http://www.cooley.com", getTestHtml("samples/cooley.com.html"));
        assertEquals("Not as expected!", "http://www.cooley.com/favicon.ico", parse);
    }

    @Test
    public void parse_wordpress() throws Exception {
        String parse = FaviconParser.parse("http://www.wordpress.org", getTestHtml("samples/wordpress.org.html"));
        assertEquals("Not as expected!", "http://s.w.org/favicon.ico?2", parse);
    }

    @Test
    public void parse_wish() throws Exception {
        String parse = FaviconParser.parse("http://www.wish.com", getTestHtml("samples/wish.com.html"));
        assertEquals("Not as expected!", "https://main.cdn.wish.com/850f6c8a6753/img/ic_launcher_wish.png", parse);
    }

    private String getTestHtml(String s) throws IOException, URISyntaxException {
        return new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(s).toURI())));
    }
}