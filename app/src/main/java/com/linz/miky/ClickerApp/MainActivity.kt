package com.linz.miky.ClickerApp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.linz.miky.ClickerApp.util.rotate90
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import com.linz.miky.ClickerApp.viewmodel.CountViewModel
import com.linz.miky.cpsc356.R
import java.util.*

class MainActivity : AppCompatActivity() {
  private lateinit var countViewModel: CountViewModel

  private var babyCounter: Long = 0
  private fun getUsername() = intent.extras?.get("username").toString().toLowerCase(Locale.US)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    countViewModel = ViewModelProviders.of( this).get(CountViewModel::class.java)
    countViewModel.getUserCount(getUsername()).observe( this,
      android.arch.lifecycle.Observer { updateCounter(it!!) })

    myButton.setOnClickListener {
      countViewModel.setUserCount(getUsername(), babyCounter + 1) }
    }

  private fun updateCounter(count: Long) {
    babyCounter = count
    myTextView.text = babyCounter.toString()
  }

}
