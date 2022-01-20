package domain.catalogo.event;

import domain.generic.DomainEvent;

import javax.validation.constraints.NotBlank;

public class PeliculaAgregada extends DomainEvent {

    @NotBlank
    private final String peliculaId;
    @NotBlank
    private final String titulo;
    @NotBlank
    private final String sinopsis;
    @NotBlank
    private final String year;
    @NotBlank
    private final String url;

    public PeliculaAgregada(String id, String titulo, String sinopsis, String year,String url) {
        super("sofka.catalogo.peliculaagregada");
        this.peliculaId = id;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.year = year;
        this.url = url;
    }

    @Override
    public String getId() {
        return peliculaId;
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

    public String getUrl() {
        return url;
    }
}
