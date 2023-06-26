package com.example.weatherappkg.data.network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author: Dzhaparov Bekmamat
 */
class ApiClient {
    //создает экземпляр Retrofit и настраивает клиента для выполнения сеетвых запросов в приложении
    fun retrofitClient(): WeatherApiService {//наследуется от WAS
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")//возвращает настройку Retrofit с указанием базового URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()//GsonConverterFactory - преобразует JSON-ответы в обьекты модели данных
            .create(WeatherApiService::class.java)/*
            create(WeatherApiService::class.java) - это вызов метода create() объекта Retrofit.Builder(),
             который создает экземпляр WeatherApiService на основе интерфейса WeatherApiService.*/
    }
}