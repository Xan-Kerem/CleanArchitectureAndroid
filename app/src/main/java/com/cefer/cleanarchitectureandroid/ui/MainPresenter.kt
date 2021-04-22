package com.cefer.cleanarchitectureandroid.ui

import com.cefer.domain.Location as DomainLocation
import com.cefer.usecases.GetLocations
import com.cefer.usecases.RequestNewLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(
    private var view: View?,
    private val getLocations: GetLocations,
    private val requestNewLocation: RequestNewLocation
) {
    interface View {
        fun renderLocations(locations: List<Location>)
    }


    fun onCreate() = GlobalScope.launch(Dispatchers.Main) {
        val locations = withContext(Dispatchers.IO) { getLocations() }
        view?.renderLocations(locations.map(DomainLocation::toPresentationModel))
    }

    fun newLocationClicked() = GlobalScope.launch(Dispatchers.Main) {
        val locations = withContext(Dispatchers.IO) { requestNewLocation() }
        view?.renderLocations(locations.map(DomainLocation::toPresentationModel))
    }

    fun onDestroy() {
        view = null
    }
}