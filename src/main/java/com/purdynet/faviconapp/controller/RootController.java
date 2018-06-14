package com.purdynet.faviconapp.controller;

import com.purdynet.faviconapp.service.FaviconService;
import com.purdynet.faviconapp.service.FreemarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RootController {

    private final FreemarkerService freemarkerService;
    private final FaviconService faviconService;

    @Autowired
    public RootController(final FreemarkerService freemarkerService, final FaviconService faviconService) {
        this.freemarkerService = freemarkerService;
        this.faviconService = faviconService;
    }

    @GetMapping(value = "/")
    public String getRoot(@RequestParam(name = "refresh", required = false) boolean refresh, @RequestParam(name = "url", required = false) String url) {
        Map<String,Object> dataModel = new HashMap<>();
        dataModel.put("faviconUrl", faviconService.faviconUrl(url, refresh));
        return freemarkerService.processTemplate("templates/root.ftl", dataModel);
    }

    @GetMapping(value = {"/refresh","/refresh/{limit}"})
    public String getFaviconRefresh(@PathVariable(value="limit", required = false) Integer limit) {
        Integer limitPassed = (limit != null ? limit : 100);
        faviconService.refreshFaviconUrls(limitPassed);
        return String.format("Refresh trigger for top %d initiatied!", limitPassed);
    }
}
