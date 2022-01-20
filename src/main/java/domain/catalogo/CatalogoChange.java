package domain.catalogo;

import domain.catalogo.event.CatalogoAgregado;
import domain.catalogo.event.PeliculaAgregada;
import domain.generic.EventChange;

import java.util.HashSet;
import java.util.UUID;

public class CatalogoChange implements EventChange {
    public CatalogoChange(Catalogo catalogo) {
        listener((CatalogoAgregado event) -> {
            catalogo.titulo = event.getTitulo();
            catalogo.peliculas = new HashSet<>();
        });

        listener((PeliculaAgregada event) -> {
            catalogo.peliculas.add(
                    new Pelicula(UUID.randomUUID().toString(), event.getTitulo(),
                            event.getSinopsis(), event.getYear(), event.getUrl()));
        });
    }
}
