package com.cefer.data

import com.cefer.domain.Location


class LocationsRepository(private val locationPersistenceSource: LocationPersistenceSource,
                          private val deviceLocationSource: DeviceLocationSource) {
    fun getSavedLocations() = locationPersistenceSource.getPersistedLocations()

    fun requestNewLocation(): List<Location> {
        val newLocation = deviceLocationSource.getDeviceLocation()
        locationPersistenceSource.saveNewLocation(newLocation)
        return getSavedLocations()
    }
}

interface LocationPersistenceSource {
    fun getPersistedLocations(): List<Location>

    fun saveNewLocation(location: Location)
}

interface DeviceLocationSource {
    fun getDeviceLocation(): Location
}