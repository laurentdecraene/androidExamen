package com.ldc.android.travelchecker.data.API

import com.ldc.android.travelchecker.data.model.LoginDTO
import com.ldc.android.travelchecker.data.model.Trip

class TravelAPIHelper(private val service:TravelAPIservice) {
    suspend fun login(dto:LoginDTO)= service.login(dto)
    suspend fun getTrips(token:String)=service.getTrips(token)
    suspend fun addTrip(token:String,trip: Trip)=service.addTrip(token,trip)
    suspend fun getTrip(token:String,id:Int)=service.getTrip(token,id)
}