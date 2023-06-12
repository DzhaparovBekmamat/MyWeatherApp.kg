package com.example.weatherappkg.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappkg.data.model.WeatherModel
import com.example.weatherappkg.data.repository.WeatherRepository

/**
 * Author: Dzhaparov Bekmamat
 */
class MainViewModel: ViewModel() {

    private val repository = WeatherRepository()

    var livedata : LiveData<WeatherModel> = MutableLiveData()

    fun getWeather() {
        livedata = repository.getWeather()
    }

}