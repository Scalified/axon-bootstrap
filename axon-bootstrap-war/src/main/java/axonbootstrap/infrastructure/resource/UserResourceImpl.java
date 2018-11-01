package axonbootstrap.infrastructure.resource;

import axonbootstrap.domain.command.CreateUserCommand;
import axonbootstrap.infrastructure.query.GetUserQuery;
import axonbootstrap.infrastructure.query.model.UserEntry;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Stateless
public class UserResourceImpl implements UserResource {

    @Inject
    private CommandGateway commandGateway;

    @Inject
    private QueryGateway queryGateway;

    @Override
    public Response getById(@NotNull String id) {
        GetUserQuery query = new GetUserQuery(UUID.fromString(id));
        return queryGateway.query(query, UserEntry.class)
                .thenApply(entity -> Response.ok(entity).build())
                .join();
    }

    @Override
    public Response create(@Valid CreateUserCommand command) {
        return commandGateway.send(command)
                .thenApply(result -> Response.accepted().build())
                .join();
    }

}
