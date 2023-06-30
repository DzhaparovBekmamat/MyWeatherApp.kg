package com.example.weatherappkg.ui

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.weatherappkg.R
import com.example.weatherappkg.adapter.WeatherAdapter
import com.example.weatherappkg.databinding.ActivityMainBinding
import com.example.weatherappkg.ui.searchFragment.SearchFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchFragment.Result {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WeatherAdapter
    private val handler = Handler()
    private lateinit var currentTimeRunnable: Runnable
    private lateinit var dayTimeRunnable: Runnable
    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val searchFragment = SearchFragment(this)
            searchFragment.show(supportFragmentManager, "TAG")
        }
        recyclerView = findViewById(R.id.recycler_view)
        adapter = WeatherAdapter()
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter
        viewModel.getWeather("Bishkek")
        getWeather()
        currentTimeRunnable = object : Runnable {
            override fun run() {
                binding.dayTimeText.text = getCurrentTime()
                handler.postDelayed(this, 1000)
            }
        }

        dayTimeRunnable = object : Runnable {
            override fun run() {
                val currentWeatherData = viewModel.livedata.value
                if (currentWeatherData != null) {
                    val localtimeEpoch = currentWeatherData.location.localtime_epoch
                    val tzId = currentWeatherData.location.tz_id
                    binding.dayTimeText.text = formattingHour(localtimeEpoch.toLong(), tzId)
                }
                handler.postDelayed(this, 1000)
            }
        }
    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getWeather() {
        viewModel.livedata.observe(this) { weatherData ->
            binding.temperature.text = "${weatherData.current.temp_c}°C"
            binding.humidityPercent.text = "${weatherData.current.humidity}%"
            binding.pressureMeasure.text = "${weatherData.current.pressure_mb}мБар"
            binding.windSpeed.text = "${weatherData.current.wind_kph}км/ч"
            val words = weatherData.current.condition.text.split(" ")
            val formattedText = words.joinToString("\n")
            binding.tempSolutionText.text = formattedText
            binding.textViewDate.text = weatherData.location.localtime
            val imageUrl = weatherData.current.condition.icon
            Glide.with(this).load("https://$imageUrl")
                .transition(DrawableTransitionOptions.withCrossFade()).into(binding.tempSolution)
            binding.maxTemp.text = "${weatherData.forecast.forecastday[0].day.maxtemp_c}°C"
            binding.minTemp.text = "${weatherData.forecast.forecastday[0].day.mintemp_c}°C"
            binding.sunriseTime.text = weatherData.forecast.forecastday[0].astro.sunrise
            binding.sunsetTime.text = weatherData.forecast.forecastday[0].astro.sunset
            binding.button.text = weatherData.location.name + ", " + weatherData.location.country
            binding.dayTimeText.text = formattingHour(
                weatherData.location.localtime_epoch.toLong(), weatherData.location.tz_id
            )
        }
    }

    override fun onResume() {
        super.onResume()
        handler.post(currentTimeRunnable)
        handler.post(dayTimeRunnable)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(currentTimeRunnable)
        handler.removeCallbacks(dayTimeRunnable)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
        return dateFormat.format(Date())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formattingHour(toLong: Long, tzId: String): String {
        val instant = Instant.ofEpochSecond(toLong)
        val dataTime = instant.atZone(ZoneId.of(tzId))
        val changer = DateTimeFormatter.ofPattern("hh:mm:ss", Locale.ENGLISH)
        return changer.format(dataTime)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun click(name: String) {
        viewModel.getWeather(name)
        getWeather()
    }
}
