package com.carlosvega.ScreenMatch;

import com.carlosvega.ScreenMatch.model.ConvierteDatos;
import com.carlosvega.ScreenMatch.model.DatosEpisodio;
import com.carlosvega.ScreenMatch.model.DatosSerie;
import com.carlosvega.ScreenMatch.model.DatosTemporada;
import com.carlosvega.ScreenMatch.service.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		String jsonResponse =  consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=breaking+bad&apikey=214a8fd4");
		//initialize conversor
		ConvierteDatos conversor= new ConvierteDatos();
		//deserialize
		var datosSerie = conversor.obtenerDatos(jsonResponse, DatosSerie.class);

		//deserialize episode one
//		String jsonEpisodeResponse = consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=breaking+bad&Season=1&episode=1&apikey=214a8fd4");
//		var datosEpisodio = conversor.obtenerDatos(jsonEpisodeResponse, DatosEpisodio.class);
//		System.out.println(datosEpisodio);

		//deserialize all seasons
		List<DatosTemporada> datosTemporadas = new ArrayList<>();
		for (int i = 1; i <= datosSerie.totalTemporadas(); i++) {
			String jsonTemporadas = consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=breaking+bad&Season=" + i + "&apikey=214a8fd4");
			var datosTemporada = conversor.obtenerDatos(jsonTemporadas,DatosTemporada.class);
			datosTemporadas.add(datosTemporada);
		}
		//show list of seasons
		datosTemporadas.forEach(System.out::println);
	}
}
