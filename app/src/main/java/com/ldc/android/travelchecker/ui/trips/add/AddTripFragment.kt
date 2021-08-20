package com.ldc.android.travelchecker.ui.trips.add

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.gson.Gson
import com.ldc.android.travelchecker.data.API.RetrofitBuilder
import com.ldc.android.travelchecker.data.API.SessionManager
import com.ldc.android.travelchecker.data.API.TravelAPIHelper
import com.ldc.android.travelchecker.data.model.LoggedInUser
import com.ldc.android.travelchecker.data.repositories.TripRepository
import com.ldc.android.travelchecker.data.room.TravelDatabase
import com.ldc.android.travelchecker.databinding.FragmentAddTripListDialogBinding


// TODO: Customize parameter argument names
const val ARG_ITEM_COUNT = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    AddTripFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class AddTripFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddTripListDialogBinding
    private lateinit var viewModel: AddTripViewModel
    private lateinit var user: LoggedInUser
    private lateinit var manager: SessionManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAddTripListDialogBinding.inflate(inflater)
        manager = SessionManager(this.requireContext())

        viewModel =ViewModelProvider(this,AddTripViewModelFactory(TripRepository(TravelAPIHelper(RetrofitBuilder.apiService), TravelDatabase.getInstance(this.requireContext()),manager),
           )).get(AddTripViewModel::class.java)
        binding.addTrip.setOnClickListener {
        viewModel.addTrip(binding.tripTitle.text.toString(),binding.destName.text.toString(),binding.startDate.text.toString(),binding.endDate.text.toString())
        }
        viewModel.created.observe(viewLifecycleOwner, Observer {
            if(it){
                dismiss()
            }
        })
        return binding.root
    }

   /* private fun openDatePicker() {
        val builder = MaterialDatePicker.Builder.datePicker().setInputMode(MaterialDatePicker.INPUT_MODE_TEXT).setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        val picker = builder.build()
        picker.addOnPositiveButtonClickListener {

        }
        picker.show(this.parentFragmentManager, "DATE_PICKER")
    }*/


}