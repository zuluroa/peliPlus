package usecase;

import com.google.gson.Gson;
import domain.catalogo.command.DatoPeliculaCommand;
import domain.generic.DomainEvent;
import domain.generic.EventStoreRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class ExtraerDatoPelicula implements Function<DatoPeliculaCommand, List<DomainEvent>> {

    private static final String URL_BASE = "https://pelisplus.so/";
    private final EventStoreRepository repository;

    public ExtraerDatoPelicula(EventStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(DatoPeliculaCommand command) {
        var pelicula = repository.getEventsBy("catalogo", command.getId());
       try {
           Connection.Response response = getResponse(command.getUrl());
           System.out.println("Response--->" + response);
           //new Gson().fromJson(response.body(), DataResponse.class).getData().stream()
        } catch (IOException e) {
            throw new ExtractPeliculaException();
        }
        return null;
    }

    private Connection.Response getResponse(String path) throws IOException {
        return Jsoup.connect(URL_BASE+"pelicula/"+path)
                .userAgent("Mozilla/5.0")
                .timeout(10 * 1000)
                .method(Connection.Method.POST)
                .followRedirects(true)
                .execute();
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}
