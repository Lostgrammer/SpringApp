package com.carlosvega.desafioAPI.principal;

import com.carlosvega.ScreenMatch.service.ConsumoAPI;
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
        List<ListaAmiibo> listaAmiibos = new ArrayList<>();

        var mapListAmiibos = transformadorJson.obtenerConvertirJson(json, ListaAmiibo.class);
        System.out.println(mapListAmiibos);

//        //mostrar json de amiibo especifico
//        System.out.println("ingrese el nombre de un amiibo que quiere buscar:");
//        amiiboNombre = input.nextLine();
//        json = consumoAPI.obtenerDatos(URL_BASE + URL_NOMBRE_AMIIBO + amiiboNombre);
//        System.out.println(json);
    }
}
