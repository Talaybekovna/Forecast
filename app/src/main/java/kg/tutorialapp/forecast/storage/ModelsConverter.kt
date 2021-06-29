package kg.tutorialapp.forecast.storage

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kg.tutorialapp.forecast.CurrentForeCast

class ModelsConverter {

    fun fromCurrentForeCastToJson(foreCast: CurrentForeCast): String =
        Gson().toJson(foreCast)

    fun fromJsonToCurrentForeCast(json: String): CurrentForeCast =
        Gson().fromJson(json, object: TypeToken<CurrentForeCast>() {}.type)
}