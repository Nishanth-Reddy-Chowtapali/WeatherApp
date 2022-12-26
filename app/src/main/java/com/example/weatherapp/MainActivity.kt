package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipcodeEditText = findViewById<TextView>(R.id.zipcodeEditText)
        val submitButton = findViewById<Button>(R.id.submitButton)


        submitButton.setOnClickListener {
         val zipcode: String = zipcodeEditText.text.toString()


         if (zipcode.length != 5){
                Toast.makeText(this, "Please enter a valid zipcode", Toast.LENGTH_SHORT).show()
            }
            else {
                forecastRepository.loadForecast(zipcode)
            }
        }
        val weeklyForecastObserver = Observer<List<DailyForecast>>{forecastItems ->
            //update our List adaptor
            Toast.makeText(this, "Loaded Items", Toast.LENGTH_SHORT).show()
        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)

    }
}