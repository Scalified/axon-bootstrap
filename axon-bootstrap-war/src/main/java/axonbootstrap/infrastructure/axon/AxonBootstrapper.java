package axonbootstrap.infrastructure.axon;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Slf4j
@Singleton
@Startup
public class AxonBootstrapper {

    private Boolean started;

    @Inject
    private Configuration configuration;

    @PostConstruct
    private void start() {
        configuration.start();
        started = true;
        log.info("Started Axon configuration");
    }

    @PreDestroy
    private void shutdown() {
        if (started) {
            configuration.shutdown();
            started = false;
            log.info("Stopped Axon configuration");
        }
    }

}
