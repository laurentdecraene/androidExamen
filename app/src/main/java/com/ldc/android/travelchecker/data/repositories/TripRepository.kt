package com.ldc.android.travelchecker.data.repositories

import androidx.lifecycle.LiveData
import com.ldc.android.travelchecker.data.API.SessionManager
import com.ldc.android.travelchecker.data.API.TravelAPIHelper
import com.ldc.android.travelchecker.data.model.Trip
import com.ldc.android.travelchecker.data.room.TravelDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TripRepository(private val apiHelper: TravelAPIHelper,val db:TravelDatabase,val manager: SessionManager) {

    val trips : LiveData<List<Trip>> = db.tripDatabaseDao.getTrips()

    suspend fun refreshTrips(){
        withContext(Dispatchers.IO){
            val trips = manager.fetchAuthToken()?.let { apiHelper.getTrips(it) }
            if (trips != null) {
                db.tripDatabaseDao.insertAll(*trips.toTypedArray())
            }
        }
    }

     fun getTrips():List<Trip>{
     return trips.value!!
    }
    suspend fun getTrip(id:Int):Trip{
        var trip:Trip
        withContext(Dispatchers.IO){
            trip= db.tripDatabaseDao.getTrip(id)!!
        }
       return trip
    }

    suspend fun addTrip(trip:Trip){
        manager.fetchAuthToken()?.let {apiHelper.addTrip(it,trip)}
        refreshTrips()
    }
}