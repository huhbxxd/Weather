package com.example.weather.core.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.example.weather.R
import dagger.android.support.DaggerAppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseActivity: DaggerAppCompatActivity() {

    companion object {
        const val UNIX_VALUE = 1000
    }

    abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
    }

    @SuppressLint("SimpleDateFormat")
    open fun dateFormatter(unix: Int, pattern: String): String {
        return SimpleDateFormat(pattern)
            .format(Date(unix.toLong() * UNIX_VALUE))
    }

    open fun onStartedBefore() {
        val sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .apply {
//                putBoolean(MainActivityOld.STARTED_BEFORE, true)
                apply()
            }
    }
}