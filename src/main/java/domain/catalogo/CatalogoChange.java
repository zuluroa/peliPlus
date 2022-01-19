package domain.catalogo;

import domain.catalogo.event.CatalogoAgregado;
import domain.catalogo.event.PeliculaAgregada;
import domain.generic.EventChange;

import java.util.HashMap;

public class CatalogoChange implements EventChange {
    public CatalogoChange(Catalogo catalogo) {
        listener((CatalogoAgregado event) -> {
            catalogo.titulo = event.getTitulo();
            catalogo.peliculas = new HashMap<>();
        });

        listener((PeliculaAgregada event) -> {
            catalogo.peliculas.put(event.getId(),
                    new Pelicula(event.getId(), event.getTitulo(),
                            event.getSinopsis(), event.getYear(),
                            event.getUrl()));
        });
    }
}
