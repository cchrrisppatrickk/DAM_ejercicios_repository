package com.example.helloword.services;

import com.example.helloword.models.PokemonDetail;
import com.example.helloword.models.PokemonListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface PokeApiService {
    //    @GET("pokemon")
//    Call<PokemonListResponse> getPokemonList(@Query("limit") int limit);
    @GET("pokemon")
    Call<PokemonListResponse> getPokemonList(
            @Query("limit") int limit,
            @Query("offset") int offset
    );

    @GET
    Call<PokemonDetail> getPokemonDetail(@Url String url);
}