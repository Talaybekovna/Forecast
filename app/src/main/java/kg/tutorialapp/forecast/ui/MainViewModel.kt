package kg.tutorialapp.forecast.ui

import androidx.lifecycle.ViewModel
import kg.tutorialapp.forecast.repo.WeatherRepo

class MainViewModel(private val weatherRepo: WeatherRepo) : ViewModel() {
}