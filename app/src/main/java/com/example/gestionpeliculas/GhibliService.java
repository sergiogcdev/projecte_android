package com.example.gestionpeliculas;

import com.example.gestionpeliculas.database.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GhibliService {

    @GET("films/")
    Call<List<Pelicula>> getFilms();

    @GET("films/{film_id}")
    Call<Pelicula> getFilm(@Path("film_id") int id);


}

