package kg.tutorialapp.forecast

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kg.tutorialapp.forecast.storage.ForeCastDatabase

class MainActivity : AppCompatActivity() {

    private val db by lazy {
        ForeCastDatabase.getInstance(applicationContext)
    }

    lateinit var tvCounter: TextView
    lateinit var btnStart: Button
    lateinit var btnToast: Button
    private var workResult = 0

    lateinit var etId: EditText
    lateinit var etLat: EditText
    lateinit var etLong: EditText
    lateinit var etDescr: EditText
    lateinit var btnInsert: Button
    lateinit var btnUpddate: Button
    lateinit var btnDelete: Button
    lateinit var btnQuery: Button
    lateinit var btnQueryAll: Button
    lateinit var tvList: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        tvCounter = findViewById(R.id.counter)
        btnStart = findViewById(R.id.btn_start)
        btnToast = findViewById(R.id.btn_toast)*/

        etId = findViewById(R.id.et_id)
        etLat = findViewById(R.id.et_lat)
        etLong = findViewById(R.id.et_long)
        etDescr = findViewById(R.id.et_description)
        btnInsert = findViewById(R.id.btn_insert)
        btnUpddate = findViewById(R.id.btn_update)
        btnDelete = findViewById(R.id.btn_delete)
        btnQuery = findViewById(R.id.btn_query)
        btnQueryAll = findViewById(R.id.btn_query_all)
        tvList = findViewById(R.id.tv_forecast_list)

        setup()
    }

    private fun setup() {
        btnInsert.setOnClickListener {
            db.forecastDao()
                .insert(getForecastFromInput())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {  }
        }

        btnUpddate.setOnClickListener {
            db.forecastDao()
                .update(getForecastFromInput())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {  }
        }

        btnDelete.setOnClickListener {
            db.forecastDao()
                .delete(getForecastFromInput())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {  }
        }

        btnQueryAll.setOnClickListener {
            db.forecastDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    var text = ""

                    it.forEach {
                        text += toString()
                    }

                    tvList.text = text
                },{

                })
        }

        btnQuery.setOnClickListener {
            db.forecastDao()
                .deleteAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
//                    tvList.text = it.toString()
                },{

                })
        }
    }

    private fun getForecastFromInput(): ForeCast {
        val id = etId.text?.toString().takeIf { !it.isNullOrEmpty() }?.toLong()
        val lat = etLat.text?.toString().takeIf { !it.isNullOrEmpty() }?.toDouble()
        val long = etLong.text?.toString().takeIf { !it.isNullOrEmpty() }?.toDouble()
        val description = etDescr.text.toString()
        val current = CurrentForeCast(weather = listOf(Weather(description = description)))

        return ForeCast(id = id, lat = lat, lon = long, current = current)
    }


    @SuppressLint("CheckResult")
    private fun makeRxCall() {
        WeatherClient.weatherApi.fetchWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
/*                    val tvText1: TextView = findViewById(R.id.tv_text1)
                    val tvText2: TextView = findViewById(R.id.tv_text2)
                    tvText1.text = it.current?.weather!![0].description
                    tvText2.text = it.current?.temp.toString()*/
                },{
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                })
    }

}


