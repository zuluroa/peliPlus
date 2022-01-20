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

    public Pelicula(String id, String titulo, String sinopsis, String year) {
        this.id = id;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.year = year;
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

    @Override
    public String toString() {
        return "Pelicula{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
