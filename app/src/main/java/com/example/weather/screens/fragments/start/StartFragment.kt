package com.example.weather.screens.fragments.start

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.weather.R
import com.example.weather.core.base.BaseFragment
import com.example.weather.core.base.BaseVMFragment
import com.example.weather.databinding.FragmentStartBinding
import com.example.weather.view.dialog.LocationDialogFragment

class StartFragment : BaseFragment<FragmentStartBinding>() {

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionLauncher =
            requireActivity().registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted)
                    findNavController().navigate(R.id.action_startFragment_to_weatherFragment)
                else
                    findNavController().navigate(R.id.action_startFragment_to_citiesFragment)
            }

    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentStartBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(requireBinding()) {
            locationBtn.setOnClickListener {
                LocationDialogFragment.newInstance(
                    childFragmentManager,
                    ::positiveLocationCallback,
                    ::negativeLocationCallback
                )
            }
        }
    }

    private fun positiveLocationCallback() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    private fun negativeLocationCallback() {
        findNavController().navigate(R.id.action_startFragment_to_citiesFragment)
    }
}