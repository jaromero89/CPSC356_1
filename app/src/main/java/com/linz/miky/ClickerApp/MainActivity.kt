package com.linz.miky.ClickerApp

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
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


class MainActivity : AppCompatActivity() {

  private lateinit var countViewModel: CountViewModel

  private var babyCounter: Long = 0
  private fun getUsername() = intent.extras?.get("username").toString().toLowerCase(Locale.US)

  lateinit var mAdView : AdView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    MobileAds.initialize(this) {}
    mAdView = findViewById(R.id.adView)
    val adRequest = AdRequest.Builder().build()
    mAdView.loadAd(adRequest)


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

}
