package com.ldc.android.travelchecker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destination")
data class Destination (
    @PrimaryKey(autoGenerate = false)
    val id:Int=0,
    @ColumnInfo(name = "location")
    val locationName:String ="",
    @ColumnInfo(name ="street")
    val streetName:String="",
    @ColumnInfo(name ="house_number")
    val houseNumber:Int=0,
    @ColumnInfo(name ="postal")
    val postalCode:Int=0,
    @ColumnInfo(name ="city")
    val city:String=""
)