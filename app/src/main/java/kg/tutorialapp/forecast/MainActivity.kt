package kg.tutorialapp.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// UROK 51
class MainActivity : AppCompatActivity() {

    lateinit var tvCounter: TextView
    lateinit var btnStart: Button
    lateinit var btnToast: Button
    private var workResult = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCounter = findViewById(R.id.counter)
        btnStart = findViewById(R.id.btn_start)
        btnToast = findViewById(R.id.btn_toast)

//        fetchWeatherUsingQuery()
        setup()
    }

    private fun setup() {
        btnStart.setOnClickListener {
            doSomeWork()
        }

        btnToast.setOnClickListener {
            Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show()
        }
    }

    private fun doSomeWork() {
        Thread(Runnable {
            for (i in 0..4) {
                Thread.sleep(1000)
                workResult++
            }

            runOnUiThread {
                tvCounter.text = workResult.toString()
            }

/*            Handler(Looper.getMainLooper()).post(Runnable {
                tvCounter.text = workResult.toString()
            })*/

        }).start()


    }

    private fun fetchWeatherUsingQuery() {
        val call = WeatherClient.weatherApi.fetchWeatherUsingQuery(lat = 40.5140, lon = 72.0161, lang = "en")

        call.enqueue(object: Callback<ForeCast>{
            override fun onResponse(call: Call<ForeCast>, response: Response<ForeCast>) {
                if (response.isSuccessful){
                    val forecast = response.body()

                    forecast?.let {
                        val tvText1: TextView = findViewById(R.id.tv_text1)
                        val tvText2: TextView = findViewById(R.id.tv_text2)
                        tvText1.text = it.current?.weather!![0].description
                        tvText2.text = it.current?.temp.toString()
                    }
                }
            }

            override fun onFailure(call: Call<ForeCast>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }

}


