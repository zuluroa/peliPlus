package domain.catalogo;

import domain.catalogo.event.CatalogoAgregado;
import domain.catalogo.event.PeliculaAgregada;
import domain.generic.AggregateRoot;
import domain.generic.DomainEvent;

import java.util.List;
import java.util.Set;

public class Catalogo extends AggregateRoot{

    protected String titulo;
    protected Set<Pelicula> peliculas;

    private Catalogo(String id) {
        super(id);
        subscribe(new CatalogoChange(this));
    }

    public Catalogo(String id, String titulo) {
        super(id);
        this.titulo = titulo;
        subscribe(new CatalogoChange(this));
        appendChange(new CatalogoAgregado(titulo)).apply();
    }

    public static Catalogo from(String id, List<DomainEvent> events){
        var catalogo = new Catalogo(id);
        events.forEach(catalogo::applyEvent);
        return catalogo;
    }

    public void addPelicula(String id, String titulo, String sinopsis, String year, String url){
        appendChange(new PeliculaAgregada(id, titulo, sinopsis, year, url)).apply();
    }

    public String getTitulo() {
        return titulo;
    }

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }
}
