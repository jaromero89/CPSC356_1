package com.linz.miky.ClickerApp

import android.graphics.Typeface
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.linz.miky.ClickerApp.util.rotate90
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import com.linz.miky.ClickerApp.viewmodel.CountViewModel
import com.linz.miky.cpsc356.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.util.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : AppCompatActivity() {

  private var weatherData: TextView? = null

  private lateinit var countViewModel: CountViewModel

  private var babyCounter: Long = 0
  private fun getUsername() = intent.extras?.get("username").toString().toLowerCase(Locale.US)

  lateinit var mAdView : AdView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    //buttonStart.setOnClickListener(View.OnClickListener {
      ForegroundService.startService(this, "Foreground Service is running...")
   // })
   // buttonStop.setOnClickListener(View.OnClickListener {
      //ForegroundService.stopService(this)
    //})

    MobileAds.initialize(this) {}
    mAdView = findViewById(R.id.adView)
    val adRequest = AdRequest.Builder().build()
    mAdView.loadAd(adRequest)

    weatherData = findViewById(R.id.textView)

    findViewById<View>(R.id.button).setOnClickListener { getCurrentData() }
    countViewModel = ViewModelProviders.of( this).get(CountViewModel::class.java)
    countViewModel.getUserCount(getUsername()).observe( this,
      androidx.lifecycle.Observer { updateCounter(it!!) })

    myButton.setOnClickListener {
      countViewModel.setUserCount(getUsername(), babyCounter + 1) }

    }


  private fun updateCounter(count: Long) {
    babyCounter = count
    myTextView.text = babyCounter.toString()
  }

  internal fun getCurrentData() {
    val retrofit = Retrofit.Builder()
      .baseUrl(BaseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
    val service = retrofit.create(WeatherService::class.java)
    val call = service.getCurrentWeatherData(lat, lon, AppId)
    call.enqueue(object : Callback<WeatherResponse> {
      override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
        if (response.code() == 200) {
          val weatherResponse = response.body()!!

          val stringBuilder = "Country: " +
                  weatherResponse.sys!!.country +
                  "\n" +
                  "Temperature: " +
                  weatherResponse.main!!.temp +
                  "\n" +
                  "Temperature(Min): " +
                  weatherResponse.main!!.temp_min +
                  "\n" +
                  "Temperature(Max): " +
                  weatherResponse.main!!.temp_max +
                  "\n" +
                  "Humidity: " +
                  weatherResponse.main!!.humidity +
                  "\n" +
                  "Pressure: " +
                  weatherResponse.main!!.pressure

          weatherData!!.text = stringBuilder
        }
      }

      override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
        weatherData!!.text = t.message
      }
      })
    }
      companion object {

        var BaseUrl = "http://api.openweathermap.org/"
        var AppId = "3a5e571b670bba24070468114b7fb8bd"
        var lat = "33.787914"
        var lon = "-117.853104"

      }}

