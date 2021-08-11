package com.example.gestionpeliculas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestionpeliculas.database.Pelicula;
import com.example.gestionpeliculas.database.PeliculaController;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    String filmId;
    TextView tv_title, tv_score, tv_year;
    ImageView iv_photo;
    PeliculaController pController;
    Pelicula film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        pController = PeliculaController.get(this);

        tv_title = findViewById(R.id.tv_detail_title);
        tv_score = findViewById(R.id.tv_detail_score);
        tv_year = findViewById(R.id.tv_detail_year);
        iv_photo = findViewById(R.id.iv_photo);

        filmId = getIntent().getStringExtra(getString(R.string.id_intent));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        film = pController.getFilm(filmId);
        tv_title.setText(film.getTitle());
        tv_score.setText(String.valueOf(film.getRt_score()));
        tv_year.setText(String.valueOf(film.getRelease_date()));
        Picasso.get().load(film.getUrl()).into(iv_photo);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void removeFilm(View view) {
        openDialog();
    }

    public void openDialog()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(getString(R.string.app_name));
        alertDialogBuilder.setMessage(R.string.question)
                .setCancelable(false)
                .setPositiveButton(R.string.btn_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        pController.removeFilm(film);
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.btn_no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}