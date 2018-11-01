package axonbootstrap.infrastructure.resource;

import axonbootstrap.domain.command.CreateUserCommand;
import com.scalified.rest.jaxrs.extension.ExtendedMediaType;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces(ExtendedMediaType.APPLICATION_JSON_UTF_8)
public interface UserResource {

    @GET
    @Path("/{id}")
    Response getById(@NotNull @PathParam("id") String id);

    @POST
    @Path("/create")
    @Consumes(ExtendedMediaType.APPLICATION_JSON_UTF_8)
    Response create(@Valid CreateUserCommand command);

}
