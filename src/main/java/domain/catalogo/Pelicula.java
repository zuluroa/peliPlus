package domain.catalogo;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Pelicula {

    @NotBlank
    private final String id;
    @NotBlank
    private final String titulo;
    @NotBlank
    private final String sinopsis;
    @NotBlank
    private final String year;
    @NotBlank
    private final String url;

    public Pelicula(String id, String titulo, String sinopsis, String year, String url) {
        this.id = Objects.requireNonNull(id);
        this.titulo = Objects.requireNonNull(titulo);
        this.sinopsis = Objects.requireNonNull(sinopsis);
        this.year = Objects.requireNonNull(year);
        this.url = Objects.requireNonNull(url);
    }

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

    public String getUrl() {
        return url;
    }
}
