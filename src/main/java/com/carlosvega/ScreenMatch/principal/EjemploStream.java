package com.carlosvega.ScreenMatch.principal;

import java.util.Arrays;
import java.util.List;

public class EjemploStream {
    List<String> listasubs = Arrays.asList("davuxz","pit","pato","diablito","nats");
    public void mostrarStream(){
        listasubs.stream()
                .sorted()
                .limit(3)
                .map(n -> n.toUpperCase())
                .forEach(System.out::println);
    }
}
