package usecase;

import domain.catalogo.Catalogo;
import domain.catalogo.command.AgregarCatalogoCommand;
import domain.generic.DomainEvent;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Dependent
public class AgregarCatalogoUseCase implements Function<AgregarCatalogoCommand, List<DomainEvent>> {

    @Override
    public List<DomainEvent> apply(AgregarCatalogoCommand command) {
        var catalogo = new Catalogo(command.getId(), command.getTitulo());
        return catalogo.getUncommittedChanges();
    }
}
