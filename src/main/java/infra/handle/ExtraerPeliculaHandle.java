package infra.handle;

import domain.catalogo.command.DatoPeliculaCommand;
import infra.generic.UseCaseHandle;
import io.quarkus.vertx.ConsumeEvent;
import usecase.ExtraerDatoPelicula;

public class ExtraerPeliculaHandle extends UseCaseHandle {

    private final ExtraerDatoPelicula extraerDatoPelicula;

    public ExtraerPeliculaHandle(ExtraerDatoPelicula extraerDatoPelicula) {
        this.extraerDatoPelicula = extraerDatoPelicula;
    }

    @ConsumeEvent(value = "sofka.catalogo.pelicula")
    void consumeBlocking(DatoPeliculaCommand command) {
        var events = extraerDatoPelicula.apply(command);
        savePelicula(command.getId(), events);
    }
}
