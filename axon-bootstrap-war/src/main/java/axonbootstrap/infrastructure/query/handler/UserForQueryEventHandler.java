package axonbootstrap.infrastructure.query.handler;

import axonbootstrap.domain.event.UserCreatedEvent;
import axonbootstrap.infrastructure.query.model.UserEntry;
import axonbootstrap.infrastructure.query.persistence.UserEntryQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;

import javax.inject.Inject;

@Slf4j
public class UserForQueryEventHandler {

    @Inject
    private UserEntryQueryRepository repository;

    @EventHandler
    public void on(UserCreatedEvent event) {
        UserEntry entry = new UserEntry(event);
        repository.add(entry);
        log.debug("Added User Entry (id = {})", entry.getId());
    }

}
