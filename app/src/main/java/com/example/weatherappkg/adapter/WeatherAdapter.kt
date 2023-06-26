package com.example.weatherappkg.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappkg.databinding.ListItemBinding
import com.example.weatherappkg.core.UIState

class WeatherAdapter :
    ListAdapter<UIState<WeatherModels>, WeatherAdapter.WeatherViewHolder>(WeatherDiffUtil()) {
    class WeatherDiffUtil : DiffUtil.ItemCallback<UIState<WeatherModels>>() {
        override fun areItemsTheSame(
            oldItem: UIState<WeatherModels>, newItem: UIState<WeatherModels>
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: UIState<WeatherModels>, newItem: UIState<WeatherModels>
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class WeatherViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(weatherState: UIState<WeatherModels>) {
            when (weatherState) {
                is UIState.Success -> {
                    val weatherModel = weatherState.data
                    binding.imageView.setImageResource(weatherModel!!.imageView)
                    binding.textView.text = weatherModel.text
                    binding.tempMax.text = weatherModel.tempMax
                    binding.tempMin.text = weatherModel.tempMin
                }
                is UIState.Error -> {
                    // Handle error state
                }
                is UIState.Loading -> {
                    // Handle loading state
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }
}

