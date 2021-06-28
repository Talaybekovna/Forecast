package kg.tutorialapp.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kg.tutorialapp.forecast.network.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvText1: TextView = findViewById(R.id.tv_text1)
        val tvText2: TextView = findViewById(R.id.tv_text2)


        val call = weatherApi.getWeather()
        call.enqueue(object: Callback<ForeCast>{
            override fun onResponse(call: Call<ForeCast>, response: Response<ForeCast>) {
                if (response.isSuccessful){
                    val forecast = response.body()

                    forecast?.let {
                        tvText1.text = it.current?.weather!![0].description
                        tvText2.text = it.timezone
                    }
                }
            }

            override fun onFailure(call: Call<ForeCast>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private val okhttp by lazy {
        val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttp)
                .build()
    }

    private val weatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }
}