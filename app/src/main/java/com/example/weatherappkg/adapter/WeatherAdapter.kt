package com.example.weatherappkg.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappkg.databinding.ListItemBinding

class WeatherAdapter(private val items: List<Any>) :
    ListAdapter<WeatherModels, WeatherAdapter.WeatherViewHolder>(WeatherDiffUtil()) {
    class WeatherDiffUtil : DiffUtil.ItemCallback<WeatherModels>() {
        override fun areItemsTheSame(oldItem: WeatherModels, newItem: WeatherModels): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherModels, newItem: WeatherModels): Boolean {
            return oldItem == newItem
        }
    }

    inner class WeatherViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(weatherModel: WeatherModels) {
            binding.imageView.setImageResource(weatherModel.imageView)
            binding.textView.text = weatherModel.text
            binding.tempMax.text = weatherModel.tempMax
            binding.tempMin.text = weatherModel.tempMin
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeatherViewHolder(
        ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(weatherViewHolder: WeatherViewHolder, position: Int) {
        val item = getItem(position)
        weatherViewHolder.onBind(item)
    }

    fun updateData(items: List<WeatherModels>) {
        submitList(items.toMutableList())
    }
}

