package com.ldc.android.travelchecker.ui.trips.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.ldc.android.travelchecker.R
import com.ldc.android.travelchecker.data.API.RetrofitBuilder
import com.ldc.android.travelchecker.data.API.SessionManager
import com.ldc.android.travelchecker.data.API.TravelAPIHelper
import com.ldc.android.travelchecker.data.model.LoggedInUser
import com.ldc.android.travelchecker.data.repositories.TripRepository
import com.ldc.android.travelchecker.data.room.TravelDatabase
import com.ldc.android.travelchecker.databinding.FragmentTripListBinding
import com.ldc.android.travelchecker.ui.trips.TripsViewmodelFactory
import com.ldc.android.travelchecker.ui.trips.adapter.TripListAdapter
import com.ldc.android.travelchecker.ui.trips.adapter.TripListener

class TripListFragment : Fragment() {

    private lateinit var binding: FragmentTripListBinding
    private lateinit var viewModel: TripsViewModel
    private lateinit var user: LoggedInUser
    private lateinit var manager: SessionManager
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTripListBinding.inflate(inflater)
        manager = SessionManager(this.requireContext())

        viewModel = ViewModelProvider(this, TripsViewmodelFactory(TripRepository(TravelAPIHelper(RetrofitBuilder.apiService),
            TravelDatabase.getInstance(this.requireContext()),
            this.manager))).get(
            TripsViewModel::class.java)
        val adapter = TripListAdapter(TripListener { id->
            val bundle = Bundle()
            bundle.putInt("tripId", id as Int);
            this.findNavController().navigate(R.id.action_navigation_trips_to_tripDetailFragment,bundle)
        })

        binding.tripList.adapter = adapter
        viewModel.trips.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it)
            }
        })
        binding.addTripFab.setOnClickListener {
            this.findNavController().navigate(R.id.action_navigation_trips_to_addTripFragment)
        }
        return binding.root
    }
}