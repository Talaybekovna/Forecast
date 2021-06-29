package kg.tutorialapp.forecast.storage

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kg.tutorialapp.forecast.DailyForeCast
import kg.tutorialapp.forecast.HourlyForeCast

class CollectionsConverter {

    @TypeConverter
    fun fromHourlyForecastListToJson(list: List<HourlyForeCast>?): String? =
        Gson().toJson(list)

    @TypeConverter
    fun fromJsonToHourlyForeCastList(json: String?): List<HourlyForeCast>? =
        Gson().fromJson(json, object : TypeToken<List<DailyForeCast>>() {}.type)

    @TypeConverter
    fun fromDailyForecastListToJson(list: List<DailyForeCast>?): String? =
        Gson().toJson(list)

    @TypeConverter
    fun fromJsonToDailyForeCastList(json: String?): List<DailyForeCast>? =
        Gson().fromJson(json, object : TypeToken<List<DailyForeCast>>() {}.type)
}