package com.carlosvega.desafioAPI.model;

public interface ITransformaJson {
    <T> T obteneryConvertirJson(String json, Class<T> clase);
}
