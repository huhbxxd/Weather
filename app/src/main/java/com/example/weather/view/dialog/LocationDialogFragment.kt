package com.example.weather.view.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.weather.R

class LocationDialogFragment(
    private val positiveCallback: () -> Unit,
    private val negativeCallback: () -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        return AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.allow_location_title))
            .setMessage(getString(R.string.provide_location))
            .setPositiveButton(getString(R.string.allow)) { _, _ ->
                positiveCallback()
            }
            .setNegativeButton(getString(R.string.not_allow)) { _, _ ->
                negativeCallback
            }
            .create()
    }

    companion object {
        fun newInstance(
            fragmentManager: FragmentManager,
            positiveCallback: () -> Unit,
            negativeCallback: () -> Unit
        ) {
            LocationDialogFragment(positiveCallback, negativeCallback).show(fragmentManager, TAG)
        }

        private const val TAG = "DialogShowed"
    }
}