package com.example.weather.screens.fragments.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.core.base.BaseVMFragment
import com.example.weather.data.response.cities.CitiesFields
import com.example.weather.databinding.FragmentCitiesBinding
import com.example.weather.screens.fragments.cities.model.CitiesViewModel
import com.example.weather.screens.fragments.cities.ui.SearchCitiesAdapter
import com.example.weather.utils.log

class CitiesFragment : BaseVMFragment<FragmentCitiesBinding, CitiesViewModel>() {

    private val adapterCities: SearchCitiesAdapter by lazy {
        SearchCitiesAdapter(::onClickCity)
    }
    private var queryText = ""

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentCitiesBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(requireBinding().citiesTab) {
            recyclerCities.apply {
                adapter = adapterCities

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (!recyclerView.canScrollVertically(DIRECTION_BOT)
                            && newState == RecyclerView.SCROLL_STATE_IDLE
                            && adapterCities.hasLoading
                        ) {
                            viewModel.setQuery(queryText)
                            adapterCities.hasLoading = false
                        }
                    }
                })
            }

            viewModel.citiesLiveData.observe(viewLifecycleOwner) { cities ->
                adapterCities.setCities(cities.citiesRecords ?: listOf())
            }

            search.doAfterTextChanged { text ->
                viewModel.setQuery(text?.toString())
            }
        }
    }

    private fun onClickCity(city: CitiesFields) {
        city.log()
    }

    companion object {
        private const val DIRECTION_BOT = 1
    }



}