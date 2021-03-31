package com.example.weather.screens.choose

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.screens.cities.CitiesActivity
import com.example.weather.screens.main.MainActivity
import kotlinx.android.synthetic.main.activity_choose.*
import java.security.Permissions


class ChooseActivity: BaseActivity() {

    override val layout: Int
        get() = R.layout.activity_choose

    private companion object {
        val TITLE_ALLERT = "Allow Weather to access your location?"
        val TEXT_ALERT = "Allow Weather to access to provide local content"
        val NEGATIVE_ALLERT = "DON\'T ALLOW"
        val POSITIVE_ALLERT = "ALLOW"
        val startedBefore = "startedBefore"
    }

    override fun onResume() {
        super.onResume()

    }

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentToCities = Intent(this, CitiesActivity::class.java)
        val intentToMain = Intent(this, MainActivity::class.java)

        val  requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) {
                    startActivity(intentToMain)
                    finish()
                } else {
                    Toast.makeText(this, "Location denied", Toast.LENGTH_SHORT).show()
                    startActivity(intentToCities)
                    finish()
                }
            }

        byLocation.setOnClickListener {

            when {
                ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                    startActivity(intentToMain)
                    finish()
                }

                else -> {
                    AlertDialog.Builder(this)
                        .setTitle(TITLE_ALLERT)
                        .setMessage(TEXT_ALERT)
                        .setPositiveButton(POSITIVE_ALLERT, object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                            }
                        })
                        .setNegativeButton(NEGATIVE_ALLERT, object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                startActivity(intentToCities)
                                finish()
                            }
                        })
                        .show()
                }

            }
        }
    }

}