package infra.generic;

import domain.generic.DomainEvent;
import domain.generic.EventStoreRepository;
import domain.generic.StoredEvent;
import infra.message.BusService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public abstract class UseCaseHandle {
    @Inject
    private EventStoreRepository repository;

    @Inject
    private BusService busService;

    public void savePelicula(String catalogoId, List<DomainEvent> events) {
        events.stream().map(event -> {
            String eventBody = EventSerializer.instance().serialize(event);
            return new StoredEvent(event.getClass().getTypeName(), new Date(), eventBody);
        }).forEach(storedEvent -> repository.saveEvent("catalogo", catalogoId, storedEvent));

        events.forEach(busService::send);
    }
}
