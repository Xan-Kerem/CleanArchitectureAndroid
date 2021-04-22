package com.cefer.cleanarchitectureandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cefer.cleanarchitectureandroid.databinding.ActivityMainBinding
import com.cefer.cleanarchitectureandroid.framework.FakeLocationSource
import com.cefer.cleanarchitectureandroid.framework.InMemoryLocationPersistenceSource
import com.cefer.data.LocationsRepository
import com.cefer.usecases.GetLocations
import com.cefer.usecases.RequestNewLocation

class MainActivity : AppCompatActivity(), MainPresenter.View {
    private val presenter: MainPresenter
    private val locationsAdapter = LocationsAdapter()
    private lateinit var binding: ActivityMainBinding

    init {
        // This would be done by a dependency injector in a complex App
        //
        val persistence = InMemoryLocationPersistenceSource()
        val deviceLocation = FakeLocationSource()
        val locationsRepository = LocationsRepository(persistence, deviceLocation)
        presenter = MainPresenter(
            this,
            GetLocations(locationsRepository),
            RequestNewLocation(locationsRepository)
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            recycler.adapter = locationsAdapter

            newLocationBtn.setOnClickListener { presenter.newLocationClicked() }

            presenter.onCreate()
        }
    }

    override fun renderLocations(locations: List<Location>) {
        locationsAdapter.items = locations
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()

    }
}