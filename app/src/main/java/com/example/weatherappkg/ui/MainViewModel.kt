package com.example.weatherappkg.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappkg.data.model.WeatherModel
import com.example.weatherappkg.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Author: Dzhaparov Bekmamat
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: WeatherRepository
) : ViewModel() {
    //наследуется от ViewModel
//    private val repository =
//        WeatherRepository() //создается экземпляр WeatherRepository для доступа к его репозиториям
    var livedata: LiveData<WeatherModel> =
        MutableLiveData() //livedata будет содержать данные о погоде и использоваться для наблюдения за обновлениями данных

    fun getWeather(name: String) {
        livedata = repo.getWeather(name)
    }
}
