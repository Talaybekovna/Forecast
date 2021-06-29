package kg.tutorialapp.forecast

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kg.tutorialapp.forecast.storage.ForeCastDatabase

class MainActivity : AppCompatActivity() {

    private val db by lazy {
        ForeCastDatabase.getInstance(applicationContext)
    }

    lateinit var tvList: TextView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvList = findViewById(R.id.tv_forecast_list)
        button = findViewById(R.id.button)

        makeRxCall()


        db.forecastDao().getAll().observe(this, Observer {
            tvList.text = it?.toString()
        })
    }

    @SuppressLint("CheckResult")
    private fun makeRxCall() {
        WeatherClient.weatherApi.fetchWeather()
                .subscribeOn(Schedulers.io())
                .map {
                    db.forecastDao().deleteAll()
                    db.forecastDao().insert(it)
                    it
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                },{
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                })
    }

/*    @SuppressLint("CheckResult")
    private fun getFromDb() {
        db.forecastDao()
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    tvList.text = it.toString()
                },
                {

                }
            )
    }*/

}


