package com.linz.miky.ClickerApp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.linz.miky.cpsc356.CountRepository


class CountViewModel(application: Application) : AndroidViewModel(application)  {
    private val repository = CountRepository(application.applicationContext)

    fun getUserCount(name: String) = repository.getUserCount(name)

    fun setUserCount(name: String, count: Long) = repository.setUserCount(name, count)
}