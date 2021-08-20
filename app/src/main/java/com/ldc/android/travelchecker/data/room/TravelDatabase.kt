package com.ldc.android.travelchecker.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ldc.android.travelchecker.data.model.Destination
import com.ldc.android.travelchecker.data.model.Trip

@Database(entities = [Trip::class],version = 2,exportSchema = false)
abstract class TravelDatabase:RoomDatabase() {
    abstract val tripDatabaseDao:TripDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: TravelDatabase? = null
        fun getInstance(context: Context): TravelDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TravelDatabase::class.java,
                        "Travel_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance

            }
        }
    }
}
