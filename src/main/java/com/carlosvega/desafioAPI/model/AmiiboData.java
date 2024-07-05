package com.carlosvega.desafioAPI.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties (ignoreUnknown = true)
public record AmiiboData(
        @JsonAlias("amiiboSeries") String seriePerteneciente,
        @JsonAlias("character") String personaje,
        @JsonAlias("name") String personajeEspecifico,
        @JsonAlias("release" )FechaLanzamiento fechaLanzamiento

) {
}
