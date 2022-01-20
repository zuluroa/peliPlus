package domain.catalogo.command;

import domain.generic.Command;

import javax.validation.constraints.NotBlank;

public class AgregarPeliculaCommand extends Command {

    @NotBlank
    private  String peliculaId;
    @NotBlank
    private  String titulo;
    @NotBlank
    private  String sinopsis;
    @NotBlank
    private  String year;
    @NotBlank
    private String CatalogoId;
    @NotBlank
    private String url;

    public AgregarPeliculaCommand() {
    }

    public String getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(String peliculaId) {
        this.peliculaId = peliculaId;
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

    public String getCatalogoId() {
        return CatalogoId;
    }

    public void setCatalogoId(String catalogoId) {
        CatalogoId = catalogoId;
    }

    public String getUrl() {
        return url;
    }
}
