package com.example.gestionpeliculas.database;


import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class PeliculaController {

    protected static PeliculaController pController;
    private PeliculaDao dao;

    private PeliculaController(Context context) {
        Context appContext = context.getApplicationContext();
        PeliculaDatabase database = Room.databaseBuilder(appContext, PeliculaDatabase.class, "pelicula").allowMainThreadQueries().build();
        dao = database.getDao();
    }

    public static PeliculaController get(Context context) {
        if (pController == null) {
            pController = new PeliculaController(context);
        }
        return pController;
    }

    public List<Pelicula> getFilmList() {
        return dao.getFilmList();
    }

    public Pelicula getFilm(String pId)
    {
        return dao.getFilm(pId);
    }

    public void insertFilm(Pelicula p) {
        dao.insertFilm(p);
    }

    public void removeFilm(Pelicula p)
    {
        dao.removeFilm(p);
    }

}
