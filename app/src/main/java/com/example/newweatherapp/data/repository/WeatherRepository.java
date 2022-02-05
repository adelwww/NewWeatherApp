package com.example.newweatherapp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newweatherapp.common.Resource;
import com.example.newweatherapp.data.models.WeatherModel;
import com.example.newweatherapp.data.remote.WeatherApi;
import com.example.newweatherapp.room.dao.WeatherDao;
import com.example.newweatherapp.ui.weather.WeatherFragment;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private WeatherApi weatherApi;
    private WeatherDao weatherDao;
    private WeatherFragment weatherFragment = new WeatherFragment();

    @Inject
    public WeatherRepository(WeatherApi weatherApi, WeatherDao weatherDao) {
        this.weatherApi = weatherApi;
        this.weatherDao = weatherDao;
    }

    public MutableLiveData<Resource<WeatherModel>> getWeather(String city) {
        MutableLiveData<Resource<WeatherModel>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        weatherApi.getWeather(city, "0c5fa66c710ce2575a3a5f08620cc6f9", "metric")
                .enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    weatherDao.deleteAll();
                    liveData.setValue(Resource.success(response.body()));
                    weatherDao.insertAll(response.body());
                    weatherDao.update(response.body());
                } else {
                    liveData.setValue(Resource.error(null, response.message()));
                    weatherDao.delete(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                liveData.setValue(Resource.error(null, t.getLocalizedMessage()));
            }
        });
        return liveData;
    }

    public LiveData<WeatherModel> getAll() {
        return weatherDao.getAll();
    }


}
