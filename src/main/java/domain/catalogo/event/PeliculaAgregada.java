package domain.catalogo.event;

import domain.generic.DomainEvent;

import javax.validation.constraints.NotBlank;

public class PeliculaAgregada extends DomainEvent {

    @NotBlank
    private final String id;
    @NotBlank
    private final String titulo;
    @NotBlank
    private final String sinopsis;
    @NotBlank
    private final String year;

    public PeliculaAgregada(String id, String titulo, String sinopsis, String year) {
        super("sofka.catalogo.peliculaagregada");
        this.id = id;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.year = year;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getYear() {
        return year;
    }

}
