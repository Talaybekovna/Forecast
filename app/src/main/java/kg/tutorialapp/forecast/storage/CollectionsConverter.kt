package kg.tutorialapp.forecast.storage

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kg.tutorialapp.forecast.DailyForeCast
import kg.tutorialapp.forecast.HourlyForeCast

class CollectionsConverter {

    fun fromHourlyForecastListToJson(list: List<HourlyForeCast>): String =
        Gson().toJson(list)

    fun fromJsonToHourlyForeCastList(json: String): List<DailyForeCast> =
        Gson().fromJson(json, object : TypeToken<List<DailyForeCast>>() {}.type)

    fun fromDailyForecastListToJson(list: List<DailyForeCast>): String =
        Gson().toJson(list)

    fun fromJsonToDailyForeCastList(json: String): List<DailyForeCast> =
        Gson().fromJson(json, object : TypeToken<List<DailyForeCast>>() {}.type)
}