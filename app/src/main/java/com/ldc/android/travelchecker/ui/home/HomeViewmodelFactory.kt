package com.ldc.android.travelchecker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ldc.android.travelchecker.data.model.LoggedInUser
import com.ldc.android.travelchecker.data.repositories.TripRepository
import com.ldc.android.travelchecker.ui.login.ui.login.LoginViewModel

class HomeViewmodelFactory(private val user:LoggedInUser,private val tripRepository: TripRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(user,tripRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}