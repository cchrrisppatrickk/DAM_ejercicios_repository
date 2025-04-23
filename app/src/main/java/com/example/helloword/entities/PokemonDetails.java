package com.example.helloword.entities;

import com.google.gson.annotations.SerializedName;

public class PokemonDetails {
    public int id;
    public String name;
    public Sprites sprites;

    public PokemonDetails(){

    }


    public PokemonDetails(int id, String name, Sprites sprites) {
        this.id = id;
        this.name = name;
        this.sprites = sprites;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public static class Sprites {
        @SerializedName("front_default")
        public String frontDefault;
    }
}
