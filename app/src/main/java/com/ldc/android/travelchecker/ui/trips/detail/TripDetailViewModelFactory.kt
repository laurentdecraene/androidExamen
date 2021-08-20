package com.ldc.android.travelchecker.ui.trips.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ldc.android.travelchecker.data.model.LoggedInUser
import com.ldc.android.travelchecker.data.repositories.TripRepository
import com.ldc.android.travelchecker.ui.trips.list.TripsViewModel

class TripDetailViewModelFactory (private val triprepository: TripRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TripDetailViewModel::class.java)) {
            return TripDetailViewModel(triprepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}