package com.carlosvega.ScreenMatch;

import com.carlosvega.ScreenMatch.model.ConvierteDatos;
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
		String jsonResponse =  consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=breaking+bad&apikey=214a8fd4");
		System.out.println(jsonResponse); //check json api
		//deserialize json
		ConvierteDatos conversor= new ConvierteDatos();
		DatosSerie datosSerie1 = conversor.obtenerDatos(jsonResponse, DatosSerie.class); //usando como tipo de datros la clase DatosSerie
		var datos = conversor.obtenerDatos(jsonResponse, DatosSerie.class);
		System.out.println(datosSerie1);
		System.out.println(datos);
	}
}
