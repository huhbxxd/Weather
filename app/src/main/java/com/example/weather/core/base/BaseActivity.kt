package com.example.weather.core.base

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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
}