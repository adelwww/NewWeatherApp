package com.example.newweatherapp.ui.weather_detail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.newweatherapp.R;
import com.example.newweatherapp.databinding.FragmentWeatherDetailBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherDetailFragment extends Fragment{

    private FragmentWeatherDetailBinding binding;
    NavController controller;

    public WeatherDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().
                findFragmentById(R.id.nav_host);
        controller = navHostFragment.getNavController();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWeatherDetailBinding.inflate(inflater,
                container,
                false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getTransition();
    }

    private void getTransition() {
        binding.getCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              controller.navigate(WeatherDetailFragmentDirections.actionWeatherDetailFragmentToWeatherFragment().setWeatherCityName(binding.searchCity.getText().toString().trim()));
            }
        });
    }
}