package com.ldc.android.travelchecker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "trip")
data class Trip (
    @PrimaryKey(autoGenerate = false)
    val id:Int?= 0,
    @ColumnInfo(name = "title")
    val title:String = "",
   @ColumnInfo(name="destination")
    val destination: String="",
    @ColumnInfo(name ="start_date")
    val startDate:String= "",
    @ColumnInfo(name ="end_date")
    val endDate:String="",
){

}
