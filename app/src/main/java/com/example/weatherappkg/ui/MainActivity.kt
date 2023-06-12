package com.example.weatherappkg.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappkg.R
import com.example.weatherappkg.data.network.retrofit.WeatherApiService
import com.example.weatherappkg.adapter.WeatherModels
import com.example.weatherappkg.adapter.WeatherAdapter
import com.example.weatherappkg.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WeatherAdapter
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        recyclerView = findViewById(R.id.recycler_view)

        adapter = WeatherAdapter(emptyList())
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter

        viewModel.getWeather()

        viewModel.livedata.observe(this) {
            binding.temperature.text = it.current.temp_c.toString() + "°C"
            binding.humidityPercent.text = it.current.humidity.toString()+"%"
            binding.pressureMeasure.text = it.current.pressure_mb.toString()+"mBar"
            binding.windSpeed.text = it.current.wind_kph.toString()+"km/h"
        }
    }
}
