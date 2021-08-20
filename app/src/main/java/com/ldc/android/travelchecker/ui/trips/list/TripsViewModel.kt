package com.ldc.android.travelchecker.ui.trips.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ldc.android.travelchecker.data.model.LoggedInUser
import com.ldc.android.travelchecker.data.model.Trip
import com.ldc.android.travelchecker.data.repositories.TripRepository
import kotlinx.coroutines.launch
import java.lang.Exception
class TripsViewModel(private val tripRepository: TripRepository):ViewModel() {

    val trips:LiveData<List<Trip>> = tripRepository.trips

    private var _tripsLoaded= MutableLiveData<String>()
    val tripsLoaded:LiveData<String> = _tripsLoaded
    init {
        viewModelScope.launch { tripRepository.refreshTrips() }
    }



}