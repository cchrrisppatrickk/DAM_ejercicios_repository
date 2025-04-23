package com.example.helloword.entities;

import java.util.List;
public class Pokemon {
    public String name;
    public String url; // URL para obtener detalles

    public Pokemon(){

    }

    public Pokemon(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // Para la respuesta de la lista
    public static class PokemonResponse {
        public int count;
        public String next;
        public String previous;
        public List<Pokemon> results;
    }
}
