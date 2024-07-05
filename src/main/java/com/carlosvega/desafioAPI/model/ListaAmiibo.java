package com.carlosvega.desafioAPI.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ListaAmiibo(
        @JsonAlias("amiibo") List<AmiiboData> ListaAmiibo
) {
}
