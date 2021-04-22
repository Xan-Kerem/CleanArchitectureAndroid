package com.cefer.cleanarchitectureandroid.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cefer.cleanarchitectureandroid.databinding.ViewLocationItemBinding
import kotlin.properties.Delegates

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ViewLocationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(location: Location) {
            with(location) {
                binding.apply {
                    locationCoordinates.text = coordinates
                    locationDate.text = date
                }
            }
        }
    }

    var items: List<Location> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ViewLocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}