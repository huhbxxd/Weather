package com.example.weather.core.base

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.weather.screens.main.MainActivity

abstract class BaseActivity(): AppCompatActivity() {

    abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
    }
}