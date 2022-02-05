package com.example.newweatherapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.newweatherapp.data.models.WeatherModel;
import com.example.newweatherapp.room.dao.WeatherDao;

import retrofit2.Converter;

@Database(entities = {WeatherModel.class}, version = 1)
@TypeConverters(TypeConvertor.class)
public abstract class WeatherDataBase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}