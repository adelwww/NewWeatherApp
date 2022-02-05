package com.example.newweatherapp.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.newweatherapp.data.models.WeatherModel;

import java.util.List;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM weatherModel")
    LiveData<WeatherModel> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(WeatherModel model);

    @Update
    void update(WeatherModel model);

    @Delete
    void delete(WeatherModel model);

    @Query("DELETE FROM weatherModel")
    void deleteAll();

}
