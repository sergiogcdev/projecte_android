package com.example.gestionpeliculas.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PeliculaDao {

    @Query("SELECT * FROM pelicula")
    List<Pelicula> getFilmList();

    @Query("SELECT * FROM pelicula WHERE id LIKE :uuid")
    Pelicula getFilm(String uuid);

    @Insert
    void insertFilm(Pelicula p);

    @Delete
    void removeFilm(Pelicula p);

}
