package com.linz.miky.cpsc356

import android.content.Context
import android.content.SharedPreferences
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import me.ibrahimsn.library.LiveSharedPreferences

class CountRepository(context: Context) {
    private val preferences: SharedPreferences
    private val liveSharedPreferences: LiveSharedPreferences

  init {
      preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
      liveSharedPreferences = LiveSharedPreferences(preferences)
  }
    fun setUserCount(name: String, count: Long) {
        preferences.edit().putLong(name, count).apply()
    }
    fun getUserCount(name: String): LiveData<Long> =
        Transformations.map(liveSharedPreferences.listenMultiple(listOf(name), defaultValue = 0L)) {it[name] }

    companion object {
        private const val PREFS = "clickCounts"
    }
}

