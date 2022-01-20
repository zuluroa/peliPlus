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
    private final MongoClient mongoClient;
    private final EventBus bus;

    public Controller(MongoClient mongoClient, EventBus bus) {
        this.mongoClient = mongoClient;
        this.bus = bus;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/catalogo")
    public Response create(AgregarCatalogoCommand command) {
        bus.publish(command.getType(), command);
        System.out.println("type--->" + command.getType());
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
