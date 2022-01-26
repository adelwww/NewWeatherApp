package com.example.newweatherapp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newweatherapp.common.Resource;
import com.example.newweatherapp.data.models.WeatherModel;
import com.example.newweatherapp.data.remote.WeatherApi;
import com.example.newweatherapp.utils.App;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private WeatherApi weatherApi;

    @Inject
    public WeatherRepository(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

//    String apiKey = "d770f5cea34175fc021492abbfb1e4a0";
//    String units = "metric";


    public MutableLiveData<Resource<WeatherModel>> getWeather(String city) {
        MutableLiveData<Resource<WeatherModel>> liveData = new MutableLiveData<>();
        weatherApi.getWeather(city, "d770f5cea34175fc021492abbfb1e4a0", "metric")
                .enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                } else {
                    liveData.setValue(Resource.error(null, response.message()));
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                liveData.setValue(Resource.error(null, t.getLocalizedMessage()));
            }
        });
        return liveData;
    }


}
