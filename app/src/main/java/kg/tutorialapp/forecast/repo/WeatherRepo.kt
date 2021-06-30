package kg.tutorialapp.forecast.repo

import kg.tutorialapp.forecast.network.WeatherApi
import kg.tutorialapp.forecast.storage.ForeCastDatabase

class WeatherRepo(
    private val db: ForeCastDatabase,
    private val weatherApi: WeatherApi
) {
}