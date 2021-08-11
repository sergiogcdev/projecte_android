package com.example.gestionpeliculas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gestionpeliculas.database.Pelicula;
import com.example.gestionpeliculas.database.PeliculaController;

public class CreateActivity extends AppCompatActivity {

    EditText et_title, et_year, et_score, et_url;
    PeliculaController pController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        et_title = findViewById(R.id.et_title);
        et_year = findViewById(R.id.et_year);
        et_score = findViewById(R.id.et_score);
        et_url = findViewById(R.id.et_url);

        pController = PeliculaController.get(this);

    }

    public void saveClicked(View view) {

        manageCreation();

    }

    public void manageCreation(){
        String sTitle = et_title.getText().toString();
        String sYear = et_year.getText().toString();
        String sScore = et_score.getText().toString();
        String sUrl = et_url.getText().toString();

        if(!sTitle.equals("") && !sYear.equals("") && !sScore.equals("") && !sUrl.equals(""))
        {
            double iScore = Double.parseDouble(sScore);
            if(iScore >=0 && iScore <=5){
                Pelicula film = new Pelicula(sTitle, Integer.parseInt(sYear), Double.parseDouble(sScore), sUrl);
                pController.insertFilm(film);
                finish();
            }
            else {
                et_score.setError(getString(R.string.year_error));
            }
        }
        else
        {
            if(sTitle.equals("")) et_title.setError(getString(R.string.generic_error));
            if(sYear.equals("")) et_year.setError(getString(R.string.generic_error));
            if(sScore.equals("")) et_score.setError(getString(R.string.generic_error));
            if(sUrl.equals("")) et_url.setError(getString(R.string.generic_error));
        }
    }

}