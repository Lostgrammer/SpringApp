package com.carlosvega.ScreenMatch.model;

public interface IConvierteDatos {
    //T tipo de datos de retorno generico
    //declarando metodo
    <T> T obtenerDatos(String json, Class<T> clase);
}
