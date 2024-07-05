package com.carlosvega.desafioAPI;


import com.carlosvega.desafioAPI.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApiApplication.class, args);
	}

	@Override
	public void run(String... args){
		Principal principal = new Principal();
		principal.mostrarMenu();

//		EjemploStream ejemploStream = new EjemploStream();
//		ejemploStream.mostrarStream();
	}
}
