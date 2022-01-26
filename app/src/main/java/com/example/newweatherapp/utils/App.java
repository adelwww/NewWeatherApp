package com.example.newweatherapp.utils;

import android.app.Application;

import com.example.newweatherapp.data.remote.RetrofitClient;
import com.example.newweatherapp.data.remote.WeatherApi;
import com.example.newweatherapp.data.repository.WeatherRepository;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}