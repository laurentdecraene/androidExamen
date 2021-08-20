package com.ldc.android.travelchecker

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ldc.android.travelchecker.data.model.Destination
import com.ldc.android.travelchecker.data.model.Trip
import com.ldc.android.travelchecker.data.room.TravelDatabase
import com.ldc.android.travelchecker.data.room.TripDatabaseDao
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception
import kotlin.Throws

@RunWith(AndroidJUnit4::class)
class TravelDatabaseTest {
    private lateinit var tripDatabaseDao: TripDatabaseDao
    private lateinit var db:TravelDatabase

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context,TravelDatabase::class.java).allowMainThreadQueries().build()
        tripDatabaseDao = db.tripDatabaseDao

    }
    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetTrip(){


        val trip =  Trip(id = 1,title = "test",destination = "Test",startDate = "12/08/2021",endDate = "13/08/2021")
        tripDatabaseDao.insert(trip)
        val selected = tripDatabaseDao.getTrip(1)
        assertEquals(selected?.id,1)

    }
}