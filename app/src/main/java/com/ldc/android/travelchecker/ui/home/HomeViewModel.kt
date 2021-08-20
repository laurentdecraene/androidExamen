package com.ldc.android.travelchecker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ldc.android.travelchecker.data.model.LoggedInUser
import com.ldc.android.travelchecker.data.model.Trip
import com.ldc.android.travelchecker.data.repositories.TripRepository
import com.ldc.android.travelchecker.ui.login.ui.login.LoginResult
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val user:LoggedInUser,private val tripRepository: TripRepository) : ViewModel() {

    private val _loggedInUser = MutableLiveData<LoggedInUser>()
    val loggedInUser: LiveData<LoggedInUser> = _loggedInUser
    private val _trips = MutableLiveData<List<Trip>>()
    val trips: LiveData<List<Trip>> = _trips
    private val _loadingTripsFailed=MutableLiveData<String>()
    val loadingTripsFailed:LiveData<String> =_loadingTripsFailed
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    fun getTrips(){
        viewModelScope.launch {
            try {
                _trips.value = tripRepository.getTrips()
            }catch (exception:Exception){
                _loadingTripsFailed.value="Failed loading trips"
            }


        }
    }
}