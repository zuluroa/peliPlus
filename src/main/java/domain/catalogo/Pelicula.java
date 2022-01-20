package domain.catalogo;

import javax.validation.constraints.NotBlank;

public class Pelicula {

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

    public Pelicula(String peliculaId, String titulo, String sinopsis, String year, String url) {
        this.peliculaId = peliculaId;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.year = year;
        this.url = url;
    }

    public String getPeliculaId() {
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

    @Override
    public String toString() {
        return "Pelicula{" +
                "peliculaId='" + peliculaId + '\'' +
                ", titulo='" + titulo + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", year='" + year + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
