package domain.catalogo.command;

import domain.generic.Command;

public class AgregarCatalogoCommand extends Command {

    private  String id;
    private  String titulo;

    public AgregarCatalogoCommand() {
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
}
