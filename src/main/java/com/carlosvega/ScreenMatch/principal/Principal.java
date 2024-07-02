package com.carlosvega.ScreenMatch.principal;

import com.carlosvega.ScreenMatch.model.ConvierteDatos;
import com.carlosvega.ScreenMatch.model.DatosEpisodio;
import com.carlosvega.ScreenMatch.model.DatosSerie;
import com.carlosvega.ScreenMatch.model.DatosTemporada;
import com.carlosvega.ScreenMatch.service.ConsumoAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    //variables
    final String URL_BASE = "https://www.omdbapi.com/?t=";
    final String URL_APIKEY ="&apikey=214a8fd4";
    Scanner input = new Scanner(System.in);
    String serieName;
    private String requestSerieName = "Ingrese el nombre de la serie que desee buscar:";
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos conversor= new ConvierteDatos(); //initialize conversor

    //metodos
    public void mostrarMenu(){
        System.out.println(requestSerieName);
        serieName = input.nextLine();
        var jsonResponse =  consumoAPI.obtenerDatos(URL_BASE + serieName.replace(" ","+") + URL_APIKEY);
        //System.out.println(jsonResponse);

        //deserialize serie general info
		var datosSerie = conversor.obtenerDatos(jsonResponse, DatosSerie.class);
        //System.out.println(datosSerie);

        //deserialize episode one
		String jsonEpisodeResponse = consumoAPI.obtenerDatos(URL_BASE + serieName.replace(" ","+") + "&Season=1&episode=1" + URL_APIKEY);
		var datosEpisodio = conversor.obtenerDatos(jsonEpisodeResponse, DatosEpisodio.class);
		//System.out.println(datosEpisodio);

		//deserialize all seasons with all episodes
		List<DatosTemporada> datosTemporadas = new ArrayList<>();
		for (int i = 1; i <= datosSerie.totalTemporadas(); i++) {
			String jsonTemporadas = consumoAPI.obtenerDatos(URL_BASE + serieName.replace(" ","+") + "&Season=" + i + URL_APIKEY);
			var datosTemporada = conversor.obtenerDatos(jsonTemporadas,DatosTemporada.class);
			datosTemporadas.add(datosTemporada);
		}
		//show list of seasons
		//datosTemporadas.forEach(System.out::println);

//        for (int i = 0; i < datosTemporadas.size(); i++) {
//            //definiendo episodiosTemporada con el valor del json api mapeado por DatosTemporada
//            List<DatosEpisodio> episodiosTemporada = datosTemporadas.get(i).episodio();
//            var numeroTemporada = datosTemporadas.get(i).numero();
//            for (int j = 0; j < episodiosTemporada.size(); j++) {
//                var nombreEpisodio = episodiosTemporada.get(j).titulo();
//                System.out.println("Temporada " + numeroTemporada +": " + nombreEpisodio);
//            }
//        }

        datosTemporadas.forEach(t -> t.episodio().forEach(e -> System.out.println(e.titulo())));
    }
}
