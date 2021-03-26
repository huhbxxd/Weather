package com.example.weather.core.base

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.weather.screens.main.MainActivity

abstract class BaseActivity(@LayoutRes private val layout: Int): AppCompatActivity() {

//    private companion object {
//        val PERMISSION_ID = 44
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }
//
//    private fun checkPermission(): Boolean {
//        return ActivityCompat.checkSelfPermission(this,
//            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
//    }
//
//    private fun requestPermission() {
//        ActivityCompat.requestPermissions(
//            MainActivity(), arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
//            PERMISSION_ID
//        )
//    }

}