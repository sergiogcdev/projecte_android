package com.example.gestionpeliculas.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.gestionpeliculas.R;

import java.util.UUID;

@Entity(tableName = "pelicula")
public class Pelicula {
    @NonNull
    @PrimaryKey
    protected String id;
    protected String title;
    protected int release_date;
    protected double rt_score;
    protected String url;

    public Pelicula() {
    }

    public Pelicula(String titulo, int anioPublicacion, double puntuacion, String srcImg) {
        this.id = UUID.randomUUID().toString();
        this.title = titulo;
        this.release_date = anioPublicacion;
        this.rt_score = puntuacion;
        this.url = srcImg;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRelease_date() {
        return release_date;
    }

    public void setRelease_date(int release_date) {
        this.release_date = release_date;
    }

    public double getRt_score() {
        return rt_score;
    }

    public void setRt_score(double rt_score) {
        this.rt_score = rt_score;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", release_date=" + release_date +
                ", rt_score=" + rt_score +
                ", url='" + url + '\'' +
                '}';
    }

    public int getScoreColor()
    {
        if(this.rt_score >= 0 && this.rt_score < 2) return (R.color.red);
        else if(this.rt_score >= 2 && this.rt_score < 4) return (R.color.black);
        else if(this.rt_score >= 4 && this.rt_score <= 5) return (R.color.green);
        else return (R.color.black);
    }
}
