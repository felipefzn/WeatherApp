package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etCityName: EditText = findViewById(R.id.etCityName)
        val btnGetWeather: Button = findViewById(R.id.btnGetWeather)

        btnGetWeather.setOnClickListener {
            val cityName = etCityName.text.toString().trim()
            if (cityName.isNotEmpty()) {
                val intent = Intent(this, WeatherActivity::class.java).apply {
                    putExtra("Nome da Cidade", cityName)
                }
                startActivity(intent)
            } else {
                etCityName.error = "Nome da Cidade Obrigatório!"
            }
        }
    }
}
