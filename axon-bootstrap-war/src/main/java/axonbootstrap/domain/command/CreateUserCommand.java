package axonbootstrap.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.io.Serializable;
import java.util.UUID;

@Data
public class CreateUserCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TargetAggregateIdentifier
    private UUID id;

    private String firstName;

    private String lastName;

}
