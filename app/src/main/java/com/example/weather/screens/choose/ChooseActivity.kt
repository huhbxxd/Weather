package com.example.weather.screens.choose

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.screens.main.MainActivity
import kotlinx.android.synthetic.main.choose_activity.*
import kotlin.properties.Delegates

class ChooseActivity: BaseActivity(R.layout.choose_activity) {

    private companion object {
        val REQUEST_CODE = 1 // random value for permission
    }

    private var permissionState by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionState = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)


        byCityName.setOnClickListener {

        }

        byLocation.setOnClickListener {
            if (permissionState != PackageManager.PERMISSION_GRANTED && android.os.Build.VERSION.SDK_INT >= 26) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

}