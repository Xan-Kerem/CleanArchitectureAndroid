package com.cefer.usecases

import com.cefer.data.LocationsRepository
import com.cefer.domain.Location

class RequestNewLocation(private val locationsRepository: LocationsRepository) {
    operator fun invoke(): List<Location> = locationsRepository.requestNewLocation()
}