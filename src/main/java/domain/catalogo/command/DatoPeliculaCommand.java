package domain.catalogo.command;

import domain.generic.Command;

import javax.validation.constraints.NotBlank;

public class DatoPeliculaCommand extends Command {

    @NotBlank
    private String CatalogoId;

    public DatoPeliculaCommand() {
    }

    public String getCatalogoId() {
        return CatalogoId;
    }

    public void setCatalogoId(String catalogoId) {
        CatalogoId = catalogoId;
    }
}
