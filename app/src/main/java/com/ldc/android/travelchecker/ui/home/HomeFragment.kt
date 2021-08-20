package com.ldc.android.travelchecker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.ldc.android.travelchecker.data.API.RetrofitBuilder
import com.ldc.android.travelchecker.data.API.SessionManager
import com.ldc.android.travelchecker.data.API.TravelAPIHelper
import com.ldc.android.travelchecker.data.model.LoggedInUser
import com.ldc.android.travelchecker.data.repositories.LoginRepository
import com.ldc.android.travelchecker.data.repositories.TripRepository
import com.ldc.android.travelchecker.data.room.TravelDatabase
import com.ldc.android.travelchecker.databinding.FragmentHomeBinding
import com.ldc.android.travelchecker.ui.login.ui.login.LoginViewModel
import com.ldc.android.travelchecker.ui.login.ui.login.LoginViewModelFactory


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding:FragmentHomeBinding
    private lateinit var manager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        manager = SessionManager(this.requireContext())
       binding  = FragmentHomeBinding.inflate(inflater)
        val gson = Gson()
        val shared = this.requireActivity().getSharedPreferences("tokens", AppCompatActivity.MODE_PRIVATE)
        val json: String = shared.getString("loggedIn", "")!!
        val user = gson.fromJson<LoggedInUser>(json, LoggedInUser::class.java)
        homeViewModel =
            ViewModelProvider(this, HomeViewmodelFactory(user,TripRepository(TravelAPIHelper(RetrofitBuilder.apiService),
                TravelDatabase.getInstance(this.requireContext()),manager)))
                .get(HomeViewModel::class.java)

       binding.textHome.text = "Welcome " + user.firstName

        homeViewModel.getTrips()
        homeViewModel.trips.observe(viewLifecycleOwner, Observer {
            it.let {
                binding.textNrTrips.text = it.count().toString() + " trip(s) gepland"
            }
        })
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}