package axonbootstrap.infrastructure.commons;

import com.scalified.jpa.Jpa;
import com.scalified.jpa.JpaImpl;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.NoTransactionManager;
import org.axonframework.config.Configuration;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.SQLStateResolver;
import org.axonframework.queryhandling.QueryBus;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SimpleQueryBus;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class CDISetup {

    @Produces
    Jpa jpa(EntityManager em) {
        return new JpaImpl(em);
    }

    @Produces
    EventStorageEngine produceEventStorageEngine(Serializer serializer, EntityManagerProvider provider) {
        return new JpaEventStorageEngine(serializer,
                null,
                new SQLStateResolver(),
                serializer,
                provider,
                NoTransactionManager.INSTANCE
        );
    }

    @Produces
    @ApplicationScoped
    Serializer produceSerializer() {
        return new JacksonSerializer();
    }

    @Produces
    @ApplicationScoped
    EntityManagerProvider produceEntityManagerProvider(EntityManager em) {
        ContainerManagedEntityManagerProvider provider = new ContainerManagedEntityManagerProvider();
        provider.setEntityManager(em);
        return provider;
    }

    @Produces
    @ApplicationScoped
    CommandBus producesCommandBus() {
        return new SimpleCommandBus();
    }

    @Produces
    @ApplicationScoped
    QueryBus producesQueryBus() {
        return new SimpleQueryBus();
    }

    @Produces
    @ApplicationScoped
    EventProcessingConfiguration producesEventProcessingConfiguration() {
        return new EventProcessingConfiguration();
    }

    @Produces
    @ApplicationScoped
    CommandGateway produceCommandGateway(Configuration configuration) {
        return configuration.commandGateway();
    }

    @Produces
    @ApplicationScoped
    QueryGateway produceQueryGateway(Configuration configuration) {
        return configuration.queryGateway();
    }

    @Produces
    @PersistenceContext(unitName = "bootstrapPU")
    EntityManager entityManger;

}
