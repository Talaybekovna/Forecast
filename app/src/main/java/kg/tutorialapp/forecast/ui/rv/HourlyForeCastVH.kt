package kg.tutorialapp.forecast.ui.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kg.tutorialapp.forecast.HourlyForeCast
import kg.tutorialapp.forecast.databinding.ItemHourlyForecastBinding
import kg.tutorialapp.forecast.format
import kg.tutorialapp.forecast.models.Constants
import kotlin.math.roundToInt

class HourlyForeCastVH(private val binding: ItemHourlyForecastBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HourlyForeCast){
        item.run {
            binding.tvTime.text = item.date?.format("HH:mm")

            item.probability?.let {
                binding.tvPrecipitation.text = "${(it * 100).roundToInt()} %"
            }

            binding.tvTemp.text = item.temp?.roundToInt()?.toString()

            Glide.with(itemView.context)
                    .load("${Constants.iconUri}${item.weather?.get(0)?.icon}${Constants.iconFormat}")
                    .into(binding.ivWeatherIcon)
        }
    }

    companion object{
        fun create(parent: ViewGroup): HourlyForeCastVH{
            val binding = ItemHourlyForecastBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            return HourlyForeCastVH(binding)
        }
    }
}