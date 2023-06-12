package com.example.weatherappkg.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherappkg.data.model.WeatherModel
import com.example.weatherappkg.data.network.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Author: Dzhaparov Bekmamat
 */
class WeatherRepository {
    private val api = ApiClient().retrofitClient()
    fun getWeather(): LiveData<WeatherModel> {
        val livedata = MutableLiveData<WeatherModel>()
        api.getCurrentWeather().enqueue(object : Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                if (response.isSuccessful) {
                    livedata.value = response.body()
                }
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.e("ololo", t.message.toString())
            }
        })
        return livedata
    }
}