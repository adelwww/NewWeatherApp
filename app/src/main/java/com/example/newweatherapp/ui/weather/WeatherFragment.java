package com.example.newweatherapp.ui.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newweatherapp.R;
import com.example.newweatherapp.common.Resource;
import com.example.newweatherapp.data.models.WeatherModel;
import com.example.newweatherapp.databinding.FragmentWeatherBinding;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;
    private WeatherViewModel viewModel;
    NavController controller;

    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().
                findFragmentById(R.id.nav_host);
        controller = navHostFragment.getNavController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setTransition();
        setUpObserves();
    }

    private void setTransition() {
        binding.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.action_weatherFragment_to_weatherDetailFragment);
            }
        });
    }

    private void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        viewModel.getWeatherData("Bishkek");
    }


    protected void setUpObserves() {
        viewModel.getWeatherData(WeatherFragmentArgs.fromBundle(getArguments()).getWeatherCityName()).observe(getViewLifecycleOwner(), new Observer<Resource<WeatherModel>>() {
            @Override
            public void onChanged(Resource<WeatherModel> weatherModelResource) {
                switch (weatherModelResource.status) {
                    case SUCCESS: {
                        String date = getTime(weatherModelResource.data.getDt(), "EEE, dd MMM yyyy  |  HH:MM:SS", "GMT+6");
                        String cityName = weatherModelResource.data.getName() + ", " + weatherModelResource.data.getSys().getCountry();
                        String iconUrl = "https://openweathermap.org/img/wn/" + weatherModelResource.data.getWeather().get(0).getIcon()
                                + "@2x.png";
                        String sunny = weatherModelResource.data.getWeather().get(0).getMain();
                        String temp = new DecimalFormat("0").format(weatherModelResource.data.getMain().getTemp());
                        String tempMax = new DecimalFormat("0").format(weatherModelResource.data.getMain().getTempMax()) + "°C";
                        String tempMin = new DecimalFormat("0").format(weatherModelResource.data.getMain().getTempMin()) + "°C";
                        String humidity = weatherModelResource.data.getMain().getHumidity() + "%";
                        String pressure = weatherModelResource.data.getMain().getPressure() + "mBar";
                        String wind = weatherModelResource.data.getWind().getSpeed() + "m/c";
                        String sunrise = getTime(weatherModelResource.data.getSys().getSunrise(), "HH:mm", "GMT+6");
                        String sunset = getTime(weatherModelResource.data.getSys().getSunset(), "HH:mm", "GMT+6");
                        Integer d = weatherModelResource.data.getSys().getSunset() - weatherModelResource.data.getSys().getSunrise();
                        String daytime = getTime(d, "HH'h' MM'm'", "GMT");

                        binding.dayTv.setText(date);
                        binding.location.setText(cityName);
                        Glide.with(requireContext()).load(iconUrl).into(binding.weatherDefineIm);
                        binding.weatherDefineTv.setText(sunny);
                        binding.temperatureTv.setText(temp);
                        binding.maxTempTv.setText(tempMax);
                        binding.miniTempTv.setText(tempMin);
                        binding.humidityTv.setText(humidity);
                        binding.pressureTv.setText(pressure);
                        binding.windTv.setText(wind);
                        binding.sunriseTv.setText(sunrise);
                        binding.sunsetTv.setText(sunset);
                        binding.dayTimeTv.setText(daytime);


                        Toast.makeText(requireActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case ERROR: {
                        Toast.makeText(requireActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case LOADING: {
                        Toast.makeText(requireActivity(), "LOADING", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });
    }

    private String getTime(Integer timeInt, String timeFormat, String gmt) {
        long time = timeInt * (long) 1000;
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        format.setTimeZone(TimeZone.getTimeZone(gmt));
        return format.format(date);
    }
}