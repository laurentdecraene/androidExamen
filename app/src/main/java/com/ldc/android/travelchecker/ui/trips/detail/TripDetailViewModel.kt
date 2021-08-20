package com.ldc.android.travelchecker.ui.trips.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ldc.android.travelchecker.data.model.Trip
import com.ldc.android.travelchecker.data.repositories.TripRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class TripDetailViewModel(private val tripRepository: TripRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    private var _trip:MutableLiveData<Trip> = MutableLiveData<Trip>()
    var trip:LiveData<Trip> = _trip

    fun getTrip(id:Int){
        viewModelScope.launch {
           _trip.value= tripRepository.getTrip(id)
        }
    }

    fun getStartDate():Long{
        var format = SimpleDateFormat("yyyy-MM-dd")
        var date = format.parse(trip.value?.startDate)
        return date.time
    }

}