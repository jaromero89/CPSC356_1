package com.linz.miky.cpsc356

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.linz.miky.cpsc356.util.rotate90
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView

class MainActivity : AppCompatActivity() {
  private var babyCounter: Long = 0
  fun getStore() = getPreferences(Context.MODE_PRIVATE)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    babyCounter = getStore().getLong(intent.extras?.get("username").toString(), babyCounter)
    intent.extras?.get("username")

    if (savedInstanceState != null) {
      updateCounter(savedInstanceState.getLong(intent.extras?.get("username").toString()))
    }else if(getStore().contains(intent.extras?.get("username").toString())) {
      myTextView.text = babyCounter.toString()
    }

    myButton.setOnClickListener {
      babyCounter++
      myTextView.text = babyCounter.toString()
      myImage.rotate90()

      myButton.text = when (babyCounter) {
        1L -> "stop"
        in 2 .. 9 -> myButton.text.toString().plus(  "!")
        else -> myButton.text
      }
    }
  }

  private fun updateCounter(count: Long) {
    babyCounter = count
    myTextView.text =babyCounter.toString()
  }

  override fun onPause() {
    super.onPause()
    getStore().edit().putLong(intent.extras?.get("username").toString(), babyCounter).apply()
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    outState?.run {
      putLong( intent.extras?.get("username").toString(), babyCounter)
    }

    super.onSaveInstanceState(outState)
  }

  companion object {
    private const val BABY_COUNTER_KEY = ""
  }
}
