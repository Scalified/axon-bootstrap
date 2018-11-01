package axonbootstrap.infrastructure.query.persistence;

import axonbootstrap.infrastructure.query.model.UserEntry;
import com.scalified.jpa.Jpa;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class UserEntryQueryRepository {

    @Inject
    private Jpa jpa;

    public void add(UserEntry entity) {
        jpa.entity(entity).insert();
    }

    public Optional<UserEntry> findById(UUID userId) {
        UserEntry entry = jpa.find(UserEntry.class).one(userId);
        return Optional.ofNullable(entry);
    }

}
