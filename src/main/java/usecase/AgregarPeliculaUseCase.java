package usecase;

import domain.catalogo.Catalogo;
import domain.catalogo.command.AgregarPeliculaCommand;
import domain.generic.DomainEvent;
import domain.generic.EventStoreRepository;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class AgregarPeliculaUseCase implements Function<AgregarPeliculaCommand, List<DomainEvent>> {

    private final EventStoreRepository repository;

    public AgregarPeliculaUseCase(EventStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AgregarPeliculaCommand command) {
        var events = repository.getEventsBy("catalogo", command.getPeliculaId());
        var catalogo = Catalogo.from(command.getCatalogoId(),events);
        catalogo.addPelicula(command.getPeliculaId(), command.getTitulo(), command.getSinopsis(), command.getYear(), command.getUrl());
        return catalogo.getUncommittedChanges();
    }
}
