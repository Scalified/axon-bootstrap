package axonbootstrap.domain.model;

import axonbootstrap.domain.command.CreateUserCommand;
import axonbootstrap.domain.event.UserCreatedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventsourcing.EventSourcingHandler;

import java.io.Serializable;
import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Data
@NoArgsConstructor
@AggregateRoot
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @AggregateIdentifier
    private UUID id;

    private String firstName;

    private String lastName;

    @CommandHandler
    public User(CreateUserCommand command) {
        apply(new UserCreatedEvent(command));
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
    }

}
