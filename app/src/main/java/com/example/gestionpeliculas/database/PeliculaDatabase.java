package com.example.gestionpeliculas.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pelicula.class}, version = 1)
public abstract class PeliculaDatabase extends RoomDatabase {
    public abstract PeliculaDao getDao();
}
