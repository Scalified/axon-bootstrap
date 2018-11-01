package axonbootstrap.infrastructure.axon;

import axonbootstrap.domain.model.User;
import axonbootstrap.infrastructure.query.handler.UserForQueryEventHandler;
import axonbootstrap.infrastructure.query.handler.UserQueryEventHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.config.*;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.queryhandling.QueryBus;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class AxonConfiguration {

    @Inject
    private TransactionManager transactionManager;

    @Inject
    private EventStorageEngine eventStorageEngine;

    @Inject
    private EventProcessingConfiguration eventProcessingConfiguration;

    @Inject
    private CommandBus commandBus;

    @Inject
    private QueryBus queryBus;

    @Produces
    @ApplicationScoped
    EventHandlingConfiguration produceEventHandlingConfiguration(UserForQueryEventHandler userForQueryEventHandler) {
        return new EventHandlingConfiguration()
                .registerEventHandler(conf -> userForQueryEventHandler);
    }

    @Produces
    @ApplicationScoped
    Configuration produceConfiguration(EventHandlingConfiguration eventHandlingConfiguration,
                                       UserQueryEventHandler userQueryEventHandler
    ) {
        return DefaultConfigurer.defaultConfiguration()
                .configureTransactionManager(conf -> transactionManager)
                .configureEmbeddedEventStore(conf -> eventStorageEngine)
                .configureCommandBus(conf -> commandBus)
                .configureQueryBus(conf -> queryBus)
                .registerModule(eventProcessingConfiguration)
                .registerModule(eventHandlingConfiguration)
                .configureAggregate(AggregateConfigurer.defaultConfiguration(User.class))
                .registerQueryHandler(conf -> userQueryEventHandler)
                .buildConfiguration();
    }

}
