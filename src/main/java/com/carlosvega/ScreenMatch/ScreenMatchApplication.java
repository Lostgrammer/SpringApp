package com.carlosvega.ScreenMatch;

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
		System.out.println(jsonResponse);
	}
}
