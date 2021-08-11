package com.example.gestionpeliculas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText et_mail, et_passwd;
    TextView tv_url;
    SharedPreferences prefs;
    String login;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_mail = findViewById(R.id.et_email);
        et_passwd = findViewById(R.id.et_password);
        tv_url = findViewById(R.id.tv_film_url);

        prefs = this.getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE);
        editor = prefs.edit();
        login = prefs.getString("login", "false");

        if(login.equals("true")) {
            Intent intent = new Intent(LoginActivity.this, ListViewScreen.class);
            startActivity(intent);
        }

        tv_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tv_url.getText().toString()));
                startActivity(browserIntent);
            }
        });


    }


    public void loginClicked(View view) {
        String sMail = et_mail.getText().toString();
        String sPasswd = et_passwd.getText().toString();

        if(sMail.equals("") || sPasswd.equals(""))
        {
            Toast.makeText(getApplicationContext(), R.string.not_empty, Toast.LENGTH_LONG).show();
        }
        else {
            editor.putString("login", "true");
            editor.commit();
            Intent intent = new Intent(LoginActivity.this, ListViewScreen.class);
            startActivity(intent);
        }

    }

}