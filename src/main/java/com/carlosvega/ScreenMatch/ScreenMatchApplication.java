package com.carlosvega.ScreenMatch;

import com.carlosvega.ScreenMatch.model.ConvierteDatos;
import com.carlosvega.ScreenMatch.model.DatosEpisodio;
import com.carlosvega.ScreenMatch.model.DatosSerie;
import com.carlosvega.ScreenMatch.service.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
//		String jsonResponse =  consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=breaking+bad&apikey=214a8fd4");
//		System.out.println(jsonResponse); //check json api response
		//initialize conversor
		ConvierteDatos conversor= new ConvierteDatos();
		//deserialize
//		DatosSerie datosSerie1 = conversor.obtenerDatos(jsonResponse, DatosSerie.class); //usando como tipo de datos la clase DatosSerie
//		var datos = conversor.obtenerDatos(jsonResponse, DatosSerie.class);
//		System.out.println(datosSerie1);
//		System.out.println(datos);
		//deserialize episode one
		String jsonEpisodeResponse = consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=breaking+bad&Season=1&episode=1&apikey=214a8fd4");
		var datosEpisodio = conversor.obtenerDatos(jsonEpisodeResponse, DatosEpisodio.class);
		System.out.println(datosEpisodio);
	}
}
