package com.purdynet.faviconapp.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.purdynet.faviconapp.service.FaviconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named("RefreshFaviconUrlActor")
@Scope("prototype")
public class RefreshFaviconUrlActor extends AbstractActor {
    private final LoggingAdapter logger = Logging.getLogger(this);

    @Autowired
    private FaviconService faviconService;

    public static Props props() {
        return Props.create(RefreshFaviconUrlActor.class);
    }

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(String.class, r -> {
                    logger.info("Refreshing " + r + "...");
                    faviconService.faviconUrl(r, true);
                    logger.info(r + " refreshed!");
                })
                .build();
    }
}
