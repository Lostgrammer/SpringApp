package com.carlosvega.desafioAPI.model;

public interface ITransformaJson {
    <T> T obtenerConvertirJson(String json, Class<T> clase);
}
