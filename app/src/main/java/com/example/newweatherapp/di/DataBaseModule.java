package com.example.newweatherapp.di;


import android.content.Context;

import androidx.room.Room;

import com.example.newweatherapp.room.dao.WeatherDao;
import com.example.newweatherapp.room.WeatherDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public static WeatherDataBase provideWeatherDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                context,
                WeatherDataBase.class,
                "weather-database"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    @Provides
    public static WeatherDao provideWeatherDao(WeatherDataBase database) {
        return database.weatherDao();
    }
}
