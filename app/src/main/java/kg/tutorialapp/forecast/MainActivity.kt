package kg.tutorialapp.forecast

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        setup()
    }

    private fun setup() {
        btnStart.setOnClickListener {
            doSomeWork()
            makeRxCall()
        }

        btnToast.setOnClickListener {
            Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("CheckResult")
    private fun makeRxCall() {
        WeatherClient.weatherApi.fetchWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val tvText1: TextView = findViewById(R.id.tv_text1)
                    val tvText2: TextView = findViewById(R.id.tv_text2)
                    tvText1.text = it.current?.weather!![0].description
                    tvText2.text = it.current?.temp.toString()
                },{
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                })
    }

    private fun doSomeWork() {
        val observable = Observable.create<String> { emitter ->
            Log.d(TAG, "${Thread.currentThread().name} starting emitting")
            Thread.sleep(3000)
            emitter.onNext("Hello")
            Thread.sleep(1000)
            emitter.onNext("Bishkek")
            emitter.onComplete()
        }

        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: String) {
                Log.d(TAG, "${Thread.currentThread().name} onNext() $t")
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        }

        observable
                .subscribeOn(Schedulers.computation())
                .map{
                    Log.d(TAG, "${Thread.currentThread().name} starting mapping")
                    it.toUpperCase()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    companion object{
        const val TAG = "RX"
    }
}


