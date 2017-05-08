package com.example.paul.testapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String API_URL = "http://api.openweathermap.org/";

    private Handler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHandler = new Handler();
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        TextView view = (TextView) findViewById(R.id.textView);
                        view.setText(new Random().nextInt() + "");
                    }
                });
            }
        }, 5, 10, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        WeatherService.Client client = retrofit.create(WeatherService.Client.class);
        Call<WeatherData> call = client.currentWeather("London");
        try {
            call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
