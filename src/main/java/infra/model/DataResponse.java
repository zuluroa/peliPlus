package infra.model;

public class DataResponse {
    String titulo;
    String contenido;

    public DataResponse(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Elemento{" +
                "titulo='" + titulo + '\'' +
                ", contenido='" + contenido + '\'' +
                '}';
    }
}
