package com.cefer.usecases

import com.cefer.data.LocationsRepository
import com.cefer.domain.Location

class GetLocations (private val locationsRepository: LocationsRepository){
    operator fun invoke():List<Location> = locationsRepository.getSavedLocations()
}