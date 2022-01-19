package infra.materialize;

import com.mongodb.client.MongoClient;
import domain.catalogo.event.PeliculaAgregada;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CatalogoHandle {

    private final MongoClient mongoClient;

    public CatalogoHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @ConsumeEvent(value = "sofka.catalogo.peliculaagregada", blocking = true)
    void consumeProgramCreated(PeliculaAgregada event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("name", event.getTitulo());

        mongoClient.getDatabase("queries")
                .getCollection("program")
                .insertOne(new Document(document));
    }
}
