package kg.tutorialapp.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kg.tutorialapp.forecast.models.Comments
import kg.tutorialapp.forecast.models.Post
import kg.tutorialapp.forecast.network.PostsApi
import kg.tutorialapp.forecast.network.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        fetchWeather()
//        fetchWeatherUsingQuery()
//        fetchPostById()
//        createPost()
//        createPostUsingFields()
//        createPostUsingFieldMap()
//        getWeatherUsingQueryMap()
        getCommentsUsingQueryMap()
    }

    private fun getCommentsUsingQueryMap() {
        val map = HashMap<String, Any>().apply {
            put("postId", Int)
            put("id", Int)
            put("name", String)
            put("email", String)
            put("body", String)
        }

        val call = postsApi.getCommentUsingQueryMap(map)
        call.enqueue(object: Callback<Comments>{
            override fun onResponse(call: Call<Comments>, response: Response<Comments>) {
                if (response.isSuccessful) {
                    val resultComment = response.body()
                    resultComment?.let {
                        val resultText = "ID: " + it.id + "\n" +
                                "userID: " + it.postId + "\n" +
                                "NAME: " + it.name + "\n" +
                                "EMAIL: " + it.email + "\n" +
                                "BODY: " + it.body + "\n"

                        val tvText1: TextView = findViewById(R.id.tv_text1)
                        tvText1.text = resultText
                    }
                }
            }

            override fun onFailure(call: Call<Comments>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getWeatherUsingQueryMap() {
        val map = HashMap<String, Any>().apply {
            put("lat", 42.882004)
            put("lon", 74.582748)
            put("exclude", "minutely")
            put("appid", "f4725e4a0fd0823ecd7f360cd4a9f45a")
            put("lang", "ru")
            put("units", "metric")
        }

        val call = weatherApi.fetchWeatherUsingQueryMap(map)

        call.enqueue(object: Callback<ForeCast>{
            override fun onResponse(call: Call<ForeCast>, response: Response<ForeCast>) {
                if (response.isSuccessful){
                    val resultGet = response.body()
                    resultGet?.let {
                        val tvText1: TextView = findViewById(R.id.tv_text1)
                        tvText1.text = it.current?.weather!![0].description
                    }
                }
            }

            override fun onFailure(call: Call<ForeCast>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun createPostUsingFieldMap() {
        val map = HashMap<String, Any>().apply {
            put("userId", 55)
            put("title", "Welcome")
            put("body", "Karakol")
        }

        val call = postsApi.createPostUsingFieldMap(map)

        call.enqueue(object: Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                val resultPost = response.body()

                resultPost?.let {
                    val resultText = "ID: " + it.id + "\n" +
                            "userID: " + it.userId + "\n" +
                            "TITLE: " + it.title + "\n" +
                            "BODY: " + it.body + "\n"

                    val tvText1: TextView = findViewById(R.id.tv_text1)
                    tvText1.text = resultText
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun createPostUsingFields() {
        val call = postsApi.createPostUsingFields(userId = 99, title = "HI", body = "OSH")

        call.enqueue(object: Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                val resultPost = response.body()

                resultPost?.let {
                    val resultText = "ID: " + it.id + "\n" +
                            "userID: " + it.userId + "\n" +
                            "TITLE: " + it.title + "\n" +
                            "BODY: " + it.body + "\n"

                    val tvText1: TextView = findViewById(R.id.tv_text1)
                    tvText1.text = resultText
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun createPost() {
        val post = Post(userId = 42, title = "Hello", body = "Bishkek")

        val call = postsApi.createPost(post)

        call.enqueue(object: Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                val resultPost = response.body()

                resultPost?.let {
                    val resultText = "ID: " + it.id + "\n" +
                            "userID: " + it.userId + "\n" +
                            "TITLE: " + it.title + "\n" +
                            "BODY: " + it.body + "\n"

                    val tvText1: TextView = findViewById(R.id.tv_text1)
                    tvText1.text = resultText
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun fetchPostById() {
        val call = postsApi.fetchPostById(id = 10)

        call.enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val post = response.body()
                post?.let {
                    val resultText = "ID: " + it.id + "\n" +
                            "userID: " + it.userId + "\n" +
                            "TITLE: " + it.title + "\n" +
                            "BODY: " + it.body + "\n"

                    val tvText1: TextView = findViewById(R.id.tv_text1)
                    tvText1.text = resultText
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun fetchWeatherUsingQuery() {
        val call = weatherApi.fetchWeatherUsingQuery(lat = 40.5140, lon = 72.0161, lang = "en")

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

    private fun fetchWeather(){
        val call = weatherApi.fetchWeather()

        call.enqueue(object: Callback<ForeCast>{
            override fun onResponse(call: Call<ForeCast>, response: Response<ForeCast>) {
                if (response.isSuccessful){
                    val forecast = response.body()

                    forecast?.let {
                        val tvText1: TextView = findViewById(R.id.tv_text1)
                        val tvText2: TextView = findViewById(R.id.tv_text2)
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
//                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttp)
                .build()
    }

    private val weatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }

    private val postsApi by lazy {
        retrofit.create(PostsApi::class.java)
    }

}


