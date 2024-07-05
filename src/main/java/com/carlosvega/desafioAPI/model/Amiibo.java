package com.carlosvega.desafioAPI.model;

import java.time.LocalDate;

public class Amiibo {
    String seriePerteneciente;
    String personaje;
    String personajeEspecifico;
    LocalDate fechaLanzamiento;

    public Amiibo(String seriePerteneciente, String personaje, String personajeEspecifico, FechaLanzamiento f) {
        this.seriePerteneciente = seriePerteneciente;
        this.personaje = personaje;
        this.personajeEspecifico = personajeEspecifico;
        try{
            this.fechaLanzamiento =LocalDate.parse(f.fechaLanzamiento());
        }catch (NullPointerException e){
            this.fechaLanzamiento = null;
        }

    }

    @Override
    public String toString() {
        return "serie= '" + seriePerteneciente + '\'' +
                ", nombre del personaje= '" + personaje + '\'' +
                ", decripcion= '" + personajeEspecifico + '\'' +
                ", fecha de lanzamiento= " + fechaLanzamiento;
    }
}
