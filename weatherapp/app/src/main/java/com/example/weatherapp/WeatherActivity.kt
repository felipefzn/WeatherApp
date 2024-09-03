package com.example.weatherapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val tvWeather: TextView = findViewById(R.id.tvWeather)
        val cityName = intent.getStringExtra("Nome da Cidade") ?: return

        CoroutineScope(Dispatchers.IO).launch {
            val apiService = RetrofitClient.retrofitInstance.create(ApiService::class.java)
            val response: Response<WeatherResponse> = apiService.getWeather(cityName, "884028df44fae021c03f928a02d935a4")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val weather = response.body()
                    val weatherInfo = "Temperatura do Local: ${weather?.main?.temp}°C\nDescrição: ${weather?.weather?.get(0)?.description}"
                    tvWeather.text = weatherInfo
                } else {
                    tvWeather.text = "Erro: ${response.message()}"
                }
            }
        }
    }
}
