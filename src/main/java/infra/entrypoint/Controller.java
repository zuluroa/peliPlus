package infra.entrypoint;

import com.mongodb.client.MongoClient;
import domain.catalogo.command.AgregarCatalogoCommand;
import domain.catalogo.command.DatoPeliculaCommand;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class Controller {

    private final EventBus bus;

    public Controller(EventBus bus) {
        this.bus = bus;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/catalogo")
    public Response create(AgregarCatalogoCommand command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/estrenos")
    public Response get(DatoPeliculaCommand command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }
}
