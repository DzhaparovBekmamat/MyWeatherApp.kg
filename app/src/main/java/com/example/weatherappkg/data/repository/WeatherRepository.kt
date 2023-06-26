package com.example.weatherappkg.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherappkg.data.model.WeatherModel
import com.example.weatherappkg.data.network.retrofit.WeatherApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Author: Dzhaparov Bekmamat
 */
class WeatherRepository @Inject constructor(
    private val api: WeatherApiService
) {
    // репозитрорий использует Retrofit для выполнения сетевых запросов и получения данных о текущей погоде
//    private val api =
//        ApiClient().retrofitClient() // экземпляр API-клиента. Это позволяет получить доступ к методам API для получения данных о погоде

    fun getWeather(): LiveData<WeatherModel> { //LiveData - это компонент архитектуры Jetpack, который обеспечивает возможность наблюдать за изменениями данных
        val livedata =
            MutableLiveData<WeatherModel>() //является подклассом и предоставляет методы для обновления данных.
        api.getCurrentWeather().enqueue(object :
            Callback<WeatherModel> { //выполнение асинхронного запроса. enqueue - позволяет отправить запрос и определить обратные вызовы для обработки более успешного овтета или ошибки.
            override fun onResponse(
                call: Call<WeatherModel>, response: Response<WeatherModel>
            ) { //обратный вызов
                if (response.isSuccessful) { //response - полученные данные о погоде
                    livedata.value =
                        response.body() //response устнавливаются в значение livedata.value, что приволдит к улучшению обновления днных внутри iveData
                }
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.e("ololo", t.message.toString())
            }
        })
        return livedata
    }
}