package com.example.helloword.services;

import com.example.helloword.entities.Color;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface ColorService {

//    https://67ff051e58f18d7209efd099.mockapi.io/colores/?limit=10&page=3

    // URL Principal -> https://67ff051e58f18d7209efd099.mockapi.io/colores/
    // URL BASE -> https://67ff051e58f18d7209efd099.mockapi.io
    // PATH -> /colores/1/
    // Query Parameters -> ?limit=10&page=1
    // BODY -> {"name":"Rojo","hex":"#FF0000"} --> Clase Color

    @GET("/colores")
    Call< List<Color> > getColors(@Query("limit") int limit, @Query("page") int page);

    @POST("/colores")
    Call<Color> create(@Body Color color);

    // {property} -> Representa un valor dinámico
    @PUT("/colores/{id}")
    Call<Color> update(@Path("id") int id, @Body Color color);

    @DELETE("/colores/{id}")
    Call<Color> delete(@Path("id") int id);
}