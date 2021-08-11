package com.example.gestionpeliculas.database;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gestionpeliculas.R;

import java.util.List;

public class PeliculaAdapter extends ArrayAdapter<Pelicula> {
    private Context context;
    private int resourceId;
    private List<Pelicula> data;
    public PeliculaAdapter(Context context, int resourceId, List<Pelicula> data)
    {
        super(context, resourceId, data);
        this.context = context;
        this.resourceId = resourceId;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = ((Activity) this.context).getLayoutInflater().inflate(this.resourceId, parent, false);
        Pelicula p = this.data.get(position);
        TextView filmTitle = (TextView) row.findViewById(R.id.tv_row_film_title);
        TextView filmScore = (TextView) row.findViewById(R.id.tv_row_film_score);
        TextView ghibliFilmTitle = (TextView) row.findViewById(R.id.tv_title_ghibli);
        if(filmTitle != null && filmScore != null) {
            filmScore.setTextColor(this.context.getResources().getColor(p.getScoreColor()));
            filmTitle.setText(p.getTitle());
            filmScore.setText(String.valueOf(p.getRt_score()));
        }
        if(ghibliFilmTitle != null)
        {
            ghibliFilmTitle.setText(p.getTitle());
        }
        return row;
    }
}
