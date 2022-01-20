package usecase;

import domain.catalogo.Catalogo;
import domain.catalogo.Pelicula;
import domain.catalogo.command.DatoPeliculaCommand;
import domain.generic.DomainEvent;
import domain.generic.EventStoreRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
        Elements peliculas = new Elements();
        var  catalogo = Catalogo.from(command.getCatalogoId(),
                repository.getEventsBy("catalogo", command.getCatalogoId())
        );
        try {
            Elements estreno = getEstreno();
            estreno.forEach(element -> {
                try {
                    peliculas.addAll(getPelicula(element.getElementsByTag("a").attr("href")));
                } catch (IOException e) {
                    throw new ExtractPeliculaException();
                }
            });
            for ( Element element: peliculas) {
                catalogo.addPelicula( UUID.randomUUID().toString(),
                        element.getElementsByTag("p").get(0).getElementsByTag("span").get(1).text(),
                        element.getElementsByTag("p").get(4).getElementsByTag("span").get(1).text(),
                        element.getElementsByTag("p").get(2).getElementsByTag("span").get(1).text());
            }
            System.out.println("Lista de elementos--->" + catalogo.getPeliculas().toString());
            return catalogo.getUncommittedChanges();
        } catch (IOException e) {
            throw new ExtractPeliculaException();
        }
    }

    private Elements getEstreno() throws IOException {
        var document = Jsoup.connect(URL_BASE + "estrenos").get();
        return document.getElementsByClass("item-pelicula pull-left");
    }

    private Elements getPelicula(String urlPelicula) throws IOException {
        var document = Jsoup.connect(URL_BASE + urlPelicula).get();
        return document.getElementsByClass("info-content");
    }

    public static String html2text(String html) {
        return Jsoup.parse(new String(html.getBytes(), StandardCharsets.UTF_8)).text();
    }
}
