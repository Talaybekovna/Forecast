package kg.tutorialapp.forecast.ui.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kg.tutorialapp.forecast.Extensions.format
import kg.tutorialapp.forecast.databinding.ItemDailyForecastBinding
import kg.tutorialapp.forecast.models.Constants
import kg.tutorialapp.forecast.models.DailyForeCast
import kotlin.math.roundToInt

class DailyForeCastVH(private val binding: ItemDailyForecastBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DailyForeCast){
        item.run {
            binding.tvWeekday.text = item.date?.format("dd/MM")

            item.probability?.let {
                binding.tvPrecipitation.text = "${(it * 100).roundToInt()} %"
            }

            binding.tvTempMax.text = item.temp?.max?.roundToInt()?.toString()
            binding.tvTempMin.text = item.temp?.min?.roundToInt()?.toString()

            Glide.with(itemView.context)
                    .load("${Constants.iconUri}${item.weather?.get(0)?.icon}${Constants.iconFormat}")
                    .into(binding.ivWeatherIcon)
        }
    }

    companion object {
        fun create(parent: ViewGroup): DailyForeCastVH {
            val binding = ItemDailyForecastBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            return DailyForeCastVH(binding)
        }
    }
}