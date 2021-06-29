package kg.tutorialapp.forecast.storage

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kg.tutorialapp.forecast.CurrentForeCast

class ModelsConverter {

    @TypeConverter
    fun fromCurrentForeCastToJson(foreCast: CurrentForeCast?): String? =
        Gson().toJson(foreCast)

    @TypeConverter
    fun fromJsonToCurrentForeCast(json: String?): CurrentForeCast? =
        Gson().fromJson(json, object: TypeToken<CurrentForeCast>() {}.type)
}