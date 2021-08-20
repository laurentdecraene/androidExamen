package com.ldc.android.travelchecker.ui.trips;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ldc.android.travelchecker.data.model.LoggedInUser
import com.ldc.android.travelchecker.data.repositories.TripRepository;
import com.ldc.android.travelchecker.ui.trips.list.TripsViewModel

import kotlin.Suppress;

class TripsViewmodelFactory(private val triprepository:TripRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TripsViewModel::class.java)) {
            return TripsViewModel(triprepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
