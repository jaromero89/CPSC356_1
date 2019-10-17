package com.linz.miky.cpsc356

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

  loginButton.setOnClickListener {
      startActivity(Intent(this, MainActivity::class.java).apply { putExtra( "username", loginUsernameField.text) })
  }
    }}





