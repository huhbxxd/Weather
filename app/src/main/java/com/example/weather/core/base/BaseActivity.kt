package com.example.weather.core.base

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.weather.R
import com.example.weather.screens.main.MainActivity
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseActivity: AppCompatActivity() {

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

    // this fun need to change preference when event itemClick will clicked
    // it's need to when user restart app while choosing city main activity not started first
    open fun onStartedBefore() {
        val sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .apply {
                putBoolean(MainActivity.STARTED_BEFORE, true)
                apply()
            }
    }
}