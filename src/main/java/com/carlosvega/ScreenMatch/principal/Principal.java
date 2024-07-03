package com.carlosvega.ScreenMatch.principal;

import com.carlosvega.ScreenMatch.model.*;
import com.carlosvega.ScreenMatch.service.ConsumoAPI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
			var datosTemporada = conversor.obtenerDatos(jsonTemporadas, DatosTemporada.class);
			datosTemporadas.add(datosTemporada);
		}
		//show list of seasons
        //datosTemporadas.forEach(t -> t.episodio().forEach(e -> System.out.println(e.titulo())));

        //create new list DatosEpisodio type
        List<DatosEpisodio> datosEpisodios = datosTemporadas.stream()
                .flatMap(t -> t.episodio().stream())
                .collect(Collectors.toList());
        //sort list to 5 best rated episodes
//        System.out.println("Top 5 episodios");
//        datosEpisodios.stream()
//                .filter(e -> ! e.rating().equalsIgnoreCase("N/A"))
//                .sorted(Comparator.comparing(DatosEpisodio::rating).reversed())
//                .limit(5)
//                .forEach(System.out::println);

        //unify numero variables from DatosTemporada and DatosEpisodio in Episodio class list
        List<Episodio> episodios = datosTemporadas.stream()
                .flatMap(t -> t.episodio().stream() //t seria cada temporada
                        .map(d -> new Episodio(t.numero(), d)))//d seria cada episodio
                .collect(Collectors.toList());
        //episodios.forEach(System.out::println);

        //filtrar resultados por año
        System.out.println("Indique a partir de que año quiere que se visualicen los episodios: ");
        var fecha = input.nextInt();
        input.nextLine();
        LocalDate localDate = LocalDate.of(fecha,1,1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodios.stream()
                .filter(e -> e.getFechaLanzamiento() != null && e.getFechaLanzamiento().isAfter(localDate))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                        ", Episodio: " + e.getNumeroEpisodio() +
                        ", Fecha de lanzamiento: " + e.getFechaLanzamiento().format(dtf)
                ));
    }
}
