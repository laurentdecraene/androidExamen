package com.ldc.android.travelchecker.ui.trips.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ldc.android.travelchecker.data.model.Trip
import com.ldc.android.travelchecker.databinding.TripListItemBinding


class TripListAdapter(val clicklistener:TripListener): ListAdapter<Trip, TripListAdapter.TripListItemHolder>(TripDiffCallback())  {



    override fun onBindViewHolder(holder: TripListItemHolder, position: Int) {


        holder.bind(getItem(position)!!,clicklistener)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripListItemHolder {
        return TripListItemHolder.from(parent)
    }

    class TripListItemHolder private constructor(val binding: TripListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(
            item: Trip,
            clicklistener: TripListener
        ) {
            binding.trip  = item
            binding.clickListener = clicklistener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): TripListItemHolder {
                val layoutInflator = LayoutInflater.from(parent.context)
               val binding= TripListItemBinding.inflate(layoutInflator,parent,false)
                return TripListItemHolder(binding)
            }
        }
    }
class TripDiffCallback:DiffUtil.ItemCallback<Trip>(){
    override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean {
       return oldItem==newItem
    }

}

}
class TripListener(val clicklistener:(tripId:Int?)->Unit){
    fun onClick(trip:Trip) = clicklistener(trip.id)
}
