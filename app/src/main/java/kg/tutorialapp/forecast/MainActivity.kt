package kg.tutorialapp.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchWeatherUsingQuery()
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


