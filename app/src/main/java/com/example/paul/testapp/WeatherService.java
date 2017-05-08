package com.example.paul.testapp;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherService {
    private static final Retrofit HELPER;

    static {
        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl url = original.url().newBuilder()
                                .addQueryParameter("appid", "b6a8c53976b6ff04bac38623b3347444")
                                .build();
                        return chain.proceed(original.newBuilder()
                                .url(url).build());
                    }
                });
        HELPER = new Retrofit.Builder().client(httpClient.build()).baseUrl(MainActivity.API_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static Client newClient() {
        return HELPER.create(Client.class);
    }

    public interface Client {
        @GET("data/2.5/weather")
        Call<WeatherData> currentWeather(@Query("q") String city);
    }
}
