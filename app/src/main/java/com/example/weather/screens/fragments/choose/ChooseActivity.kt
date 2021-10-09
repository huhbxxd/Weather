package com.example.weather.screens.fragments.choose

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.screens.fragments.cities.CitiesActivity
import com.example.weather.screens.main.MainActivity
import kotlinx.android.synthetic.main.activity_choose.*

class ChooseActivity: BaseActivity() {

    override val layout: Int
        get() = R.layout.activity_choose

    private companion object {
        const val TITLE_ALLERT = "Allow Weather to access your location?"
        const val TEXT_ALERT = "Allow Weather to access to provide local content"
        const val NEGATIVE_ALLERT = "DON\'T ALLOW"
        const val POSITIVE_ALLERT = "ALLOW"
    }

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentToCities = Intent(this, CitiesActivity::class.java)
        val intentToMain = Intent(this, MainActivity::class.java)
            .apply {
                putExtra(MainActivity.STATE_LOAD, true)
            }

        val  requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) {
                    onStartedBefore()
                    startActivity(intentToMain)
                    finish()
                } else {
                    Toast.makeText(this, "Location denied", Toast.LENGTH_SHORT).show()
                    startActivity(intentToCities)
                    finish()
                }
            }

        // continue button that provide location
        byLocation.setOnClickListener {
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) -> {
                    startActivity(intentToMain)
                    finish()
                }
                else -> {
                    AlertDialog.Builder(this)
                        .setTitle(TITLE_ALLERT)
                        .setMessage(TEXT_ALERT)
                        .setPositiveButton(POSITIVE_ALLERT
                        ) {dialog, which ->
                            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                        }
                        .setNegativeButton(NEGATIVE_ALLERT
                        ) { dialog, which ->
                            startActivity(intentToCities)
                            finish()
                        }
                        .show()
                }
            }
        }
    }

}