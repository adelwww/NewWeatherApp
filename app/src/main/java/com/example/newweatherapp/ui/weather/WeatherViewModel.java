package com.example.newweatherapp.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newweatherapp.common.Resource;
import com.example.newweatherapp.data.models.Weather;
import com.example.newweatherapp.data.models.WeatherModel;
import com.example.newweatherapp.data.repository.WeatherRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    public LiveData<Resource<WeatherModel>> liveData;
    private WeatherRepository repository;
    public LiveData<WeatherModel> localLiveData;


    @Inject
    public WeatherViewModel(WeatherRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<Resource<WeatherModel>> getWeatherData(String city) {
        return repository.getWeather(city);
    }

    public void getAll(){
        localLiveData = repository.getAll();
    }
}
