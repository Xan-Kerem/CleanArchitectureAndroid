package com.cefer.cleanarchitectureandroid.framework

import com.cefer.data.DeviceLocationSource
import com.cefer.domain.Location
import java.util.*
import kotlin.random.Random

class FakeLocationSource : DeviceLocationSource {
    override fun getDeviceLocation() =
        Location(Random.nextDouble() * (180 - 90), Random.nextDouble() * (360 - 180), Date())
}