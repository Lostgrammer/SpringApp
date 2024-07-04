package com.carlosvega.desafioAPI.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransformaJson implements ITransformaJson{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obteneryConvertirJson(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
