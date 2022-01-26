package com.example.newweatherapp.utils;

import android.app.Application;

import com.example.newweatherapp.data.remote.RetrofitClient;
import com.example.newweatherapp.data.remote.WeatherApi;
import com.example.newweatherapp.data.repository.WeatherRepository;

public class App extends Application {

    public static WeatherApi weatherApi;
    public static WeatherRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient client = new RetrofitClient();
        weatherApi = client.provideApi();
        repository = new WeatherRepository();
    }
}