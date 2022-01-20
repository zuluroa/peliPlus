package infra.materialize;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import domain.catalogo.Pelicula;
import domain.catalogo.event.CatalogoAgregado;
import domain.catalogo.event.PeliculaAgregada;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CatalogoHandle {

    private final MongoClient mongoClient;
    public CatalogoHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @ConsumeEvent(value = "sofka.catalogo.catalogoagregado", blocking = true)
    void consumeCatalogoAgregado(CatalogoAgregado event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("name", event.getTitulo());

        mongoClient.getDatabase("queries")
                .getCollection("catalogo")
                .insertOne(new Document(document));
    }

    @ConsumeEvent(value = "sofka.catalogo.peliculaagregada", blocking = true)
    void consumePeliculaAgregada(PeliculaAgregada event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getId());
        document.put("name", event.getTitulo());
        document.put("sinopsis", event.getSinopsis());
        document.put("year", event.getYear());
        document.put("url", event.getUrl());

        mongoClient.getDatabase("queries")
                .getCollection("pelicula")
                .insertOne(new Document(document));
    }
}
