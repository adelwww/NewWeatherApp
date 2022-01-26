package com.example.newweatherapp.data.remote;

import com.example.newweatherapp.data.models.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("weather")
    Call<WeatherModel> getWeather(
            @Query("q") String cityName,
            @Query("appId") String appId,
            @Query ("units") String units
    );

}
