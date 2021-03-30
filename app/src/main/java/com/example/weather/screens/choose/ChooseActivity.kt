package com.example.weather.screens.choose

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.screens.main.MainActivity
import kotlinx.android.synthetic.main.choose_activity.*
import kotlin.properties.Delegates


class ChooseActivity: BaseActivity() {

    override val layout: Int
        get() = R.layout.choose_activity

    private companion object {
        val POSITIVE = "ALLOW"
        val TITLE_FIRST = "Allow Weather to access your location?"
        val TEXT_FIRST = "Weather will use location in the background and follow" +
                " the location change to change the weather information of the notification bar"
        val NEGATIVE_FIRST = "NO, THANKS"
        val TEXT_SECOND = "Allow Weather to access to provide local content"
        val NEGATIVE_SECOND = "DON\'T ALLOW"
        val REQUEST_CODE = 1
    }
    private var permissionState by Delegates.notNull<Int>()


    private val requestPermission =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            isGranted ->
                if (isGranted) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(this, "requestPermission", Toast.LENGTH_SHORT).show()
                }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




//        permissionState = && android.os.Build.VERSION.SDK_INT >= 26
//        if (permissionState != PackageManager.PERMISSION_GRANTED && android.os.Build.VERSION.SDK_INT >= 26) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
//        } else {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }

        byLocation.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(TITLE_FIRST)
                .setMessage(TEXT_FIRST)
                .setPositiveButton(POSITIVE, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        when {
                            ContextCompat.checkSelfPermission(
                                this@ChooseActivity,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            ) == PackageManager.PERMISSION_GRANTED -> {
                                startActivity(Intent(this@ChooseActivity, MainActivity::class.java))
                            }
                            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) -> {

                            }
                        }


                    }

                })

        }
    }

}