package com.example.helloword.services;


import com.example.helloword.entities.Pokemon;
import com.example.helloword.entities.PokemonDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface PokemonService {
    //https://pokeapi.co/api/v2/pokemon?offset=40&limit=20
    @GET("/pokemon/")
    Call<Pokemon.PokemonResponse> getPokemonList(
            @Query("offset") int offset,
            @Query("limit") int limit
    );

    // Para obtener detalles específicos (lo usaremos después)
    @GET("/pokemon/{id}")
    Call<PokemonDetails> getPokemonDetails(@Path("id") int id);
}
