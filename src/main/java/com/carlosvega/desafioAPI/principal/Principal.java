package com.carlosvega.desafioAPI.principal;

import com.carlosvega.ScreenMatch.service.ConsumoAPI;
import com.carlosvega.desafioAPI.model.Amiibo;
import com.carlosvega.desafioAPI.model.FechaLanzamiento;
import com.carlosvega.desafioAPI.model.ListaAmiibo;
import com.carlosvega.desafioAPI.model.TransformaJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final String URL_BASE = "https://www.amiiboapi.com/api/amiibo/";
    private final String URL_NOMBRE_AMIIBO = "?name=";
    Scanner input = new Scanner(System.in);
    String amiiboNombre;
    ConsumoAPI consumoAPI = new ConsumoAPI();
    TransformaJson transformadorJson = new TransformaJson();
    String json;

    public void mostrarMenu (){
        //mostrar lista completa de amiibos
        json = consumoAPI.obtenerDatos(URL_BASE);
        //mapear
        var mapListAmiibos = transformadorJson.obtenerConvertirJson(json, ListaAmiibo.class);
        //ingresar datos a nueva lista de datatype Amiibo
        List<Amiibo>amiibos = new ArrayList<>();
        for (int i = 0; i < mapListAmiibos.ListaAmiibo().size(); i++) {
            var j = mapListAmiibos.ListaAmiibo().get(i);
            Amiibo amiibo = new Amiibo(j.seriePerteneciente(),
                    j.personaje(),
                    j.personajeEspecifico(), j.fechaLanzamiento());
            amiibos.add(amiibo);
        }
        amiibos.forEach(System.out::println);

//        //mostrar json de amiibo especifico
//        System.out.println("ingrese el nombre de un amiibo que quiere buscar:");
//        amiiboNombre = input.nextLine();
//        json = consumoAPI.obtenerDatos(URL_BASE + URL_NOMBRE_AMIIBO + amiiboNombre);
//        System.out.println(json);
    }
}
