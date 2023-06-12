package com.example.weatherappkg

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        val items = listOf(
            WeatherModel(R.drawable.sun, "Mon, 12", "35°C", "26°C"),
            WeatherModel(R.drawable.sun, "Tue, 13", "36°C", "27°C"),
            WeatherModel(R.drawable.sun, "Wed, 14", "37°C", "28°C")
        )

        val adapter = WeatherAdapter(items)
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            this.adapter = adapter
        }
    }
}