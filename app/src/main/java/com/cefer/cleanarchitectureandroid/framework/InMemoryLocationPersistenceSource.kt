package com.cefer.cleanarchitectureandroid.framework

import com.cefer.data.LocationPersistenceSource
import com.cefer.domain.Location

class InMemoryLocationPersistenceSource : LocationPersistenceSource {

    private val locations = mutableListOf<Location>()

    override fun getPersistedLocations() = locations

    override fun saveNewLocation(location: Location) {
        locations.add(location)
    }
}