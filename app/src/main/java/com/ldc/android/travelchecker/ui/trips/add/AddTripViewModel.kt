package com.ldc.android.travelchecker.ui.trips.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ldc.android.travelchecker.data.model.Destination
import com.ldc.android.travelchecker.data.model.LoggedInUser
import com.ldc.android.travelchecker.data.model.Trip
import com.ldc.android.travelchecker.data.repositories.TripRepository
import com.ldc.android.travelchecker.data.room.TravelDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat


class AddTripViewModel(private val tripsrepo: TripRepository ) :
    ViewModel() {
    private val _created:MutableLiveData<Boolean> = MutableLiveData(false)
    val created:LiveData<Boolean>  = _created;
    fun addTrip(title: String, destination: String, startDate: String, endDate: String) {
       var trip = Trip(null,title, destination,startDate,endDate)
        viewModelScope.launch {

            tripsrepo.addTrip(trip)
            _created.value = true;
        }

    }
}
