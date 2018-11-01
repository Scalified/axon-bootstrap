package axonbootstrap.infrastructure.query.handler;

import axonbootstrap.infrastructure.query.GetUserQuery;
import axonbootstrap.infrastructure.query.UserNotFoundException;
import axonbootstrap.infrastructure.query.model.UserEntry;
import axonbootstrap.infrastructure.query.persistence.UserEntryQueryRepository;
import org.axonframework.queryhandling.QueryHandler;

import javax.inject.Inject;
import java.util.Optional;

public class UserQueryEventHandler {

    @Inject
    private UserEntryQueryRepository userEntryQueryRepository;

    @QueryHandler
    public UserEntry on(GetUserQuery query) throws UserNotFoundException {
        Optional<UserEntry> UserEntry = userEntryQueryRepository.findById(query.getId());
        return UserEntry.orElseThrow(UserNotFoundException::new);
    }

}
