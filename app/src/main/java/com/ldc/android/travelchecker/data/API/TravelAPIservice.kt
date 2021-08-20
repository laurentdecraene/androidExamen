package com.ldc.android.travelchecker.data.API

import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import com.ldc.android.travelchecker.data.model.LoggedInUser
import com.ldc.android.travelchecker.data.model.LoginDTO
import com.ldc.android.travelchecker.data.model.Trip
import retrofit2.Call
import retrofit2.http.*

interface TravelAPIservice {
    @POST("TravelUser/Login")
     suspend fun login(@Body loginDTO: LoginDTO) : LoggedInUser
    @GET("Trip/")
    suspend fun getTrips( @Header("Authorization") authToken: String):List<Trip>
    @POST("Trip/")
    suspend fun addTrip(@Header("Authorization") authToken: String, @Body trip:Trip)

    @GET("Trip/{id}")
    suspend fun getTrip( @Header("Authorization") authToken: String,@Path("id") id:Int):Trip

}