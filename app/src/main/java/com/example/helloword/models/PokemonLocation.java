package com.example.helloword.models;

public class PokemonLocation {

    private String pokemonId;
    private String pokemonName;
    private double latitude;
    private double longitude;

    public PokemonLocation() {}  // Necesario para Firebase

    public PokemonLocation(String pokemonId, String pokemonName, double latitude, double longitude) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.latitude = latitude;
        this.longitude = longitude;
    }



}
