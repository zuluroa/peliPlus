package usecase;

import domain.catalogo.Catalogo;
import domain.catalogo.command.DatoPeliculaCommand;
import domain.generic.DomainEvent;
import domain.generic.EventStoreRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Dependent
public class ExtraerDatoPelicula implements Function<DatoPeliculaCommand, List<DomainEvent>> {

    private static final String URL_BASE = "https://pelisplus.so/";
    private final EventStoreRepository repository;

    public ExtraerDatoPelicula(EventStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(DatoPeliculaCommand command) {

        var  catalogo = Catalogo.from(command.getCatalogoId(),
                repository.getEventsBy("catalogo", command.getCatalogoId()));
        try {
            Elements estrenos = getEstrenos().select(".items-peliculas .item-pelicula a");
            for (Element element: estrenos) {
                final String urlPelicula = element.attr("href");
                final Document pelicula = Jsoup.connect("https://pelisplus.so" + urlPelicula).get();

                String titulo = pelicula.select(".info-content h1").text();
                String sinopsis = pelicula.select(".sinopsis").text();
                String year = pelicula.select(".info-content p:nth-of-type(2) span:nth-of-type(2)").text();
                String urlVideo = pelicula.select(".player.player-normal ul:nth-of-type(2)  li:nth-of-type(1)").attr("data-video");

                catalogo.addPelicula(
                        UUID.randomUUID().toString(),
                        titulo,
                        sinopsis,
                        year,
                        urlVideo
                );
            }
            return catalogo.getUncommittedChanges();
        } catch (IOException e) {
           return null;
        }
    }

    private Document getEstrenos() throws IOException {
        return Jsoup.connect(URL_BASE + "estrenos").get();
    }
}
