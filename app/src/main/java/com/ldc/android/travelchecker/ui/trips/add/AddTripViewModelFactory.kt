package com.ldc.android.travelchecker.ui.trips.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ldc.android.travelchecker.data.model.LoggedInUser
import com.ldc.android.travelchecker.data.repositories.TripRepository
import com.ldc.android.travelchecker.data.room.TravelDatabase
import com.ldc.android.travelchecker.ui.trips.list.TripsViewModel

class AddTripViewModelFactory(private val triprepository: TripRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTripViewModel::class.java)) {
            return AddTripViewModel(triprepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}