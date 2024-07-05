package com.carlosvega.ScreenMatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double rating;
    private LocalDate fechaLanzamiento;

    //constructor
    public Episodio(Integer temporada, DatosEpisodio d) {//se usa variable de la clase record DatosEpisodio
        this.temporada = temporada;
        this.titulo = d.titulo();
        this.numeroEpisodio = d.episodio();
        try {
            this.rating = Double.valueOf(d.rating()); //parsear valor String a double
        }catch (NumberFormatException e){
            this.rating = 0.0;
        }

        try{
            this.fechaLanzamiento = LocalDate.parse(d.fechaLanzamiento());
        }catch (DateTimeParseException e){
            this.fechaLanzamiento = null;
        }
    }

    //getters setters
    public Integer getTemporada() {
        return temporada;
    }
    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }
    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }
    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    //methods
    @Override
    public String toString() {
        return "temporada= " + temporada +
                ", titulo= '" + titulo + '\'' +
                ", numeroEpisodio= " + numeroEpisodio +
                ", rating= " + rating +
                ", fechaLanzamiento= " + fechaLanzamiento;
    }
}
