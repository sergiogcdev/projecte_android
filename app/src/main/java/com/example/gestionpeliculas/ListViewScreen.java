package com.example.gestionpeliculas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gestionpeliculas.database.Pelicula;
import com.example.gestionpeliculas.database.PeliculaAdapter;
import com.example.gestionpeliculas.database.PeliculaController;

import java.util.ArrayList;
import java.util.List;

public class ListViewScreen extends AppCompatActivity {


    ListView listViewFilms;
    PeliculaAdapter adapter;
    List<Pelicula> films;
    PeliculaController pController;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_screen);

        prefs = this.getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE);
        editor = prefs.edit();
        login = prefs.getString("login", "false");

        films = new ArrayList<>();

        pController = PeliculaController.get(this);

        listViewFilms = findViewById(R.id.listViewFilms);
        adapter = new PeliculaAdapter(this, R.layout.row, films);
        listViewFilms.setAdapter(adapter);

        listViewFilms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListViewScreen.this, DetailActivity.class);
                intent.putExtra(getString(R.string.id_intent), films.get(i).getId());
                startActivity(intent);
            }
        });

        if(login.equals("false") || login.equals("") || login == null)
        {
            sendToLoginActivity();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        films.clear();
        films.addAll(pController.getFilmList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(ListViewScreen.this, CreateActivity.class);
                startActivity(intent);
                break;
            case R.id.action_inspiration:
                Intent sIntent = new Intent(ListViewScreen.this, GhibliActivity.class);
                startActivity(sIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendToLoginActivity()
    {
        Intent intent = new Intent(ListViewScreen.this, LoginActivity.class);
        startActivity(intent);
    }

}