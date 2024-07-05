package com.carlosvega.desafioAPI.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FechaLanzamiento(
        @JsonAlias("eu") String fechaLanzamiento
) {
}
