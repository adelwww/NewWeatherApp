package com.example.newweatherapp.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.newweatherapp.common.Resource;
import com.example.newweatherapp.data.models.WeatherModel;
import com.example.newweatherapp.utils.App;

public class WeatherViewModel extends ViewModel {

    public LiveData<Resource<WeatherModel>> liveData;

    public WeatherViewModel() {
    }

    public void getWeather(){
        liveData= App.repository.getWeather();
    }

}
