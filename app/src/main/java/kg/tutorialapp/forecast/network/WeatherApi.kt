package kg.tutorialapp.forecast.network

import kg.tutorialapp.forecast.ForeCast
import retrofit2.Call
import retrofit2.http.GET

interface WeatherApi {

    @GET("onecall?lat=42.882004&lon=74.582748&exclude=minutely&appid=f4725e4a0fd0823ecd7f360cd4a9f45a&lang=ru&units=metric")
    fun getWeather(): Call<ForeCast>
}