package com.example.weatherappkg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter(private val items: List<WeatherModel>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weatherModel: WeatherModel) {
            val imageView = itemView.findViewById<ImageView>(R.id.image_view)
            val textView = itemView.findViewById<TextView>(R.id.text_view)
            val tempMaxTextView = itemView.findViewById<TextView>(R.id.temp_max)
            val tempMinTextView = itemView.findViewById<TextView>(R.id.temp_min)
            imageView.setImageResource(weatherModel.imageView)
            textView.text = weatherModel.text
            tempMaxTextView.text = weatherModel.tempMax
            tempMinTextView.text = weatherModel.tempMin
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, int: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = items[position]
        viewHolder.bind(item)
    }

    override fun getItemCount() = items.size
}

