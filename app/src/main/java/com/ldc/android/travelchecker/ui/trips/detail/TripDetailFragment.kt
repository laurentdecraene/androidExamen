package com.ldc.android.travelchecker.ui.trips.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.ldc.android.travelchecker.databinding.TripDetailFragmentBinding
import com.ldc.android.travelchecker.R
import com.ldc.android.travelchecker.data.API.RetrofitBuilder
import com.ldc.android.travelchecker.data.API.SessionManager
import com.ldc.android.travelchecker.data.API.TravelAPIHelper
import com.ldc.android.travelchecker.data.model.LoggedInUser
import com.ldc.android.travelchecker.data.repositories.TripRepository
import com.ldc.android.travelchecker.data.room.TravelDatabase
import com.ldc.android.travelchecker.ui.trips.TripsViewmodelFactory
import com.ldc.android.travelchecker.ui.trips.list.TripsViewModel
import java.util.*

class TripDetailFragment : Fragment() {

    private lateinit var binding:TripDetailFragmentBinding
    private lateinit var viewModel: TripDetailViewModel
    private lateinit var manager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TripDetailFragmentBinding.inflate(inflater)
        manager = SessionManager(this.requireContext())
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, TripDetailViewModelFactory(
            TripRepository(
                TravelAPIHelper(
                    RetrofitBuilder.apiService)
            , TravelDatabase.getInstance(this.requireContext()),
            this.manager))
        ).get(
            TripDetailViewModel::class.java)
       val id = this.requireArguments().getInt("tripId")

            viewModel.getTrip(id)
            viewModel.trip.observe(viewLifecycleOwner, Observer {
               it?.let {
                   binding.trip = it
               }

                binding.startDateCal.setDate(viewModel.getStartDate())
            })


    }

}