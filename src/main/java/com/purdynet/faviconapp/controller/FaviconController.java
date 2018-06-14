package com.purdynet.faviconapp.controller;

import com.purdynet.faviconapp.service.FaviconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class FaviconController {

    private final FaviconService faviconService;

    @Autowired
    public FaviconController(final FaviconService faviconService) {
        this.faviconService = faviconService;
    }

    @RequestMapping(value = {"/favicon"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getRoot(@RequestParam(name = "refresh", required = false) boolean refresh, @RequestParam(name = "url", required = false) String url) {
        return faviconService.faviconUrl(url, refresh);
    }
}
