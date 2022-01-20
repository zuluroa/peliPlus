package infra.handle;

import domain.catalogo.command.AgregarCatalogoCommand;
import infra.generic.UseCaseHandle;
import io.quarkus.vertx.ConsumeEvent;
import usecase.AgregarCatalogoUseCase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgregarCatalogoHandle extends UseCaseHandle {

    private final AgregarCatalogoUseCase agregarCatalogoUseCase;

    public AgregarCatalogoHandle(AgregarCatalogoUseCase agregarCatalogoUseCase) {
        this.agregarCatalogoUseCase = agregarCatalogoUseCase;
    }

    @ConsumeEvent(value = "sofka.catalogo.catalogo")
    void consumeBlocking(AgregarCatalogoCommand command) {
        var events = agregarCatalogoUseCase.apply(command);
        savePelicula(command.getId(), events);
    }
}
