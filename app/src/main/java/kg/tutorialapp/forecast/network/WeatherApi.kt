package kg.tutorialapp.forecast.network

import io.reactivex.Observable
import kg.tutorialapp.forecast.ForeCast
import kg.tutorialapp.forecast.Weather
import kg.tutorialapp.forecast.models.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface WeatherApi {

    @GET("onecall?lat=42.882004&lon=74.582748&exclude=minutely&appid=f4725e4a0fd0823ecd7f360cd4a9f45a&lang=ru&units=metric")
    fun fetchWeather(): Observable<ForeCast>

    @GET("onecall")
    fun fetchWeatherUsingQuery(
            @Query("lat") lat: Double = 42.882004,
            @Query("lon") lon: Double = 74.582748,
            @Query("exclude") exclude: String = "minutely",
            @Query("appid") appid: String = "f4725e4a0fd0823ecd7f360cd4a9f45a",
            @Query("lang") lang: String = "ru",
            @Query("units") units: String = "metric"
    ): Call<ForeCast>

    @GET("onecall")
    fun fetchWeatherUsingQueryMap(
            @QueryMap map: Map<String, Any>
    ): Call<ForeCast>
}