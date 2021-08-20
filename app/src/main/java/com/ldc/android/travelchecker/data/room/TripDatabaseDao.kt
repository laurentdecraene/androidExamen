package com.ldc.android.travelchecker.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ldc.android.travelchecker.data.model.Trip

@Dao
interface TripDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trip:Trip)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( vararg trip:Trip)
    @Query("Select * FROM trip")
    fun getTrips():LiveData<List<Trip>>
    @Update
    fun updateTrip(trip: Trip)
    @Query("Select  * from trip where id = :id")
    fun getTrip(id:Int):Trip?
}