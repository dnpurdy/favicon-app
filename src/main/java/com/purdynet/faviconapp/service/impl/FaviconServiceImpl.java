package com.purdynet.faviconapp.service.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.routing.RoundRobinPool;
import com.purdynet.faviconapp.dao.FaviconDAO;
import com.purdynet.faviconapp.model.FaviconRecord;
import com.purdynet.faviconapp.service.FaviconService;
import com.purdynet.faviconapp.utils.FaviconParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.stream.Stream;

import static com.purdynet.faviconapp.actor.SpringExtension.SpringExtProvider;

@Service
public class FaviconServiceImpl extends AbstractCachedService implements FaviconService {

    private static final int NUM_WORKERS = 256;

    private final ActorRef refreshFaviconUrlActor;

    private final FaviconDAO faviconDAO;

    @Autowired
    public FaviconServiceImpl(final FaviconDAO faviconDAO, final ActorSystem actorSystem) {
        this.faviconDAO = faviconDAO;
        this.refreshFaviconUrlActor = actorSystem.actorOf(SpringExtProvider.get(actorSystem).props("RefreshFaviconUrlActor").withRouter(new RoundRobinPool(NUM_WORKERS)), "refreshFaviconUrlActor");
    }

    @Override
    public String faviconUrl(final String url, final boolean getFresh) {
        if (StringUtils.isEmpty(url)) return null;

        String cleanUrl = cleanseUrl(url);

        Optional<FaviconRecord> faviconRecord = faviconDAO.getFaviconRecord(cleanUrl);

        if (getFresh || !faviconRecord.isPresent()) {
            FaviconRecord nr = new FaviconRecord(cleanUrl, FaviconParser.parse(cleanUrl, getContent(cleanUrl)));
            faviconDAO.putFaviconRecord(nr);
            return nr.getFaviconUrl();
        } else {
            return faviconRecord.get().getFaviconUrl();
        }
    }

    static String cleanseUrl(final String inUrl) {
        if (inUrl.contains("http://") || inUrl.contains("https://")) return inUrl;
        else if (inUrl.startsWith("www")) return "http://"+inUrl;
        else return "http://www."+inUrl;
    }

    @Override
    public void refreshFaviconUrls(final Integer limit) {
        Stream<String> stream = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/top-1m.csv"), Charset.forName("UTF-8"))).lines();
        stream.map(s -> s.split(","))
                .filter(strings -> new Integer(strings[0]).compareTo(limit) < 0)
                .forEach(r -> refreshFaviconUrlActor.tell(cleanseUrl(r[1]), ActorRef.noSender()));
    }

    private String getContent(String url) {
        try {
            HttpGet request = new HttpGet(url);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            return null;
        }
    }
}
