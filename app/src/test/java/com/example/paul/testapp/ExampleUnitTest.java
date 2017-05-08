package com.example.paul.testapp;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testRestClient() throws Exception {
        WeatherService.Client client = WeatherService.newClient();
        Call<WeatherData> call = client.currentWeather("London");
        try {
            Response<WeatherData> ayy = call.execute();
            System.out.println(ayy.body().getTempFahrenheit());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}