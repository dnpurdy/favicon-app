package com.purdynet.faviconapp.utils;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FaviconParser {
    private static List<Pattern> patterns = Arrays.asList(
            Pattern.compile("<link.*?rel=\"shortcut icon\".*?href=\"(.*?)\""),
            Pattern.compile("<link.*?rel=\"icon\".*?href=\"(.*?)\"")
            );

    public static String parse(final String baseUrl, final String html) {
        if (html == null) return null;
        for (Pattern p : patterns) {
            Matcher matcher = p.matcher(html);
            if (matcher.find()) {
                String match = matcher.group(1);
                if (match.startsWith("http")) return match;
                else if (match.startsWith("//")) return "http:"+match;
                else return baseUrl + match;
            }
        }
        return null;
    }
}
