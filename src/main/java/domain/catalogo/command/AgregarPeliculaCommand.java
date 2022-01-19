package domain.catalogo.command;

import domain.generic.Command;

import javax.validation.constraints.NotBlank;

public class AgregarPeliculaCommand extends Command {

    @NotBlank
    private  String id;
    @NotBlank
    private  String titulo;
    @NotBlank
    private  String sinopsis;
    @NotBlank
    private  String year;
    @NotBlank
    private  String url;

    public AgregarPeliculaCommand() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
