package axonbootstrap.domain.event;

import axonbootstrap.domain.command.CreateUserCommand;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserCreatedEvent {

    private UUID id;

    private String firstName;

    private String lastName;

    public UserCreatedEvent(CreateUserCommand command) {
        this.id = command.getId();
        this.firstName = command.getFirstName();
        this.lastName = command.getLastName();
    }

}
