package com.ldc.android.travelchecker.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ldc.android.travelchecker.data.model.Trip
import com.ldc.android.travelchecker.databinding.ActivityLoginBinding.bind

@BindingAdapter("TripTitle")
fun TextView.setTripItemTitle(item:Trip?){
    item?.let {
        text = item.title
    }
}
@BindingAdapter("TripDestLocation")
fun TextView.setTripDestLocation(item:Trip?){
    item?.let {
        text = item.destination
    }

}
@BindingAdapter("TripStartDate")
fun TextView.setTripStartDate(item:Trip?){
    item?.let {
        var date = item.startDate.split('T')[0]
        text = date
    }
}
@BindingAdapter("TripEndDate")
fun TextView.setTripEndDate(item:Trip?){
    item?.let {
        text = item.endDate
    }
}
@BindingAdapter("TripDestCity")
fun TextView.setTripDestinationCity(item:Trip?){
    item?.let {
    text = item.destination
}}