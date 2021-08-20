package com.ldc.android.travelchecker.data.model

import java.io.Serializable

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */

data class LoggedInUser(
        val id: Number,
        val firstName: String,
        val lastName:String,
        val token:String,
        val streetname:String,
        val houseNumber: Number,
        val postalCode:Number,
        val city:String,
        val trips:List<Trip>
):Serializable