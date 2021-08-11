package com.example.gestionpeliculas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gestionpeliculas.database.Pelicula;
import com.example.gestionpeliculas.database.PeliculaAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GhibliActivity extends AppCompatActivity {

    ListView lv_ghibli;
    List<Pelicula> films;
    PeliculaAdapter adapter;
    Activity activity;
    ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghibli);

        activity = this;

        lv_ghibli = findViewById(R.id.listViewGhibli);
        progressbar = findViewById(R.id.progressbar);
        films = new ArrayList<>();
        adapter = new PeliculaAdapter(this, R.layout.ghibli_row, films);
        lv_ghibli.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        listGhibliFilms();
    }

    public void listGhibliFilms()
    {
        GhibliService service = RestApiClient.getApiClientInstance().create(GhibliService.class);
        Call<List<Pelicula>> callFilm = service.getFilms();

        callFilm.enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                manageFilms(response.body());
            }

            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable t) {
                Toast.makeText(activity, R.string.server_error, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void manageFilms(List<Pelicula> filmList)
    {
        films.clear();
        films.addAll(filmList);
        adapter.notifyDataSetChanged();
        progressbar.setVisibility(View.GONE);
    }

}