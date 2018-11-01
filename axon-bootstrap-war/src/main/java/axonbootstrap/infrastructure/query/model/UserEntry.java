package axonbootstrap.infrastructure.query.model;

import axonbootstrap.domain.event.UserCreatedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class UserEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    public UserEntry(UserCreatedEvent event) {
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
    }

}
