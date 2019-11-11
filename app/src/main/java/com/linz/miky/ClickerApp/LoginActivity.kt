package com.linz.miky.ClickerApp

import android.content.Intent
import android.view.View
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.linz.miky.cpsc356.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

  loginButton.setOnClickListener {
      startActivity(Intent(this, MainActivity::class.java).apply { putExtra( "username", loginUsernameField.text) })
  }
    }}





