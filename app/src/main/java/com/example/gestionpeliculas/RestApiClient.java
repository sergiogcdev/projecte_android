package com.example.gestionpeliculas;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {

    private static final String BASE_URL = "https://ghibliapi.herokuapp.com";
    private static Retrofit retrofit;

    public static Retrofit getApiClientInstance() {
        if(retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
