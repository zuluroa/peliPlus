package domain.catalogo.event;

import domain.generic.DomainEvent;

public class CatalogoAgregado extends DomainEvent {

    private final String titulo;

    public CatalogoAgregado(String titulo) {
        super("sofka.catalogo.catalogoagregado");
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

}
