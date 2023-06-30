package com.example.weatherappkg.data.network.retrofit

import com.example.weatherappkg.data.model.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author: Dzhaparov Bekmamat
 */
interface WeatherApiService {
    //определяет методы для выполнения сетевых запросов к API погоды
    @GET("forecast.json")//гет запрос к пути forecast.json на  API погоды
    fun getCurrentWeather(
        @Query("key") apiKey: String = "f4156255de874cc29d984521231406",
        @Query("q") city: String,
        @Query("days") days: Int = 7
    ): Call<WeatherModel>//обьект Call<WeatherModel>, который представляет асинхронный запрос на получение данных о погоде
    //Call<WeatherModel> - модель данных , которая будет использоваться для десериализации JSON-ответа от API погоды
}