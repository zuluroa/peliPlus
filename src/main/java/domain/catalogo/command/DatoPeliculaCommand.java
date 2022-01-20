package domain.catalogo.command;

import domain.generic.Command;

import javax.validation.constraints.NotBlank;

public class DatoPeliculaCommand extends Command {

    @NotBlank
    private String catalogoId;

    public DatoPeliculaCommand() {
    }

    public String getCatalogoId() {
        return catalogoId;
    }

    public void setCatalogoId(String catalogoId) {
        this.catalogoId = catalogoId;
    }
}
