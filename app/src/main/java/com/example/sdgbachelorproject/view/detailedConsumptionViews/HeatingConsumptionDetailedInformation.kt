package com.example.sdgbachelorproject.view.detailedConsumptionViews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sdgbachelorproject.databinding.HeatingConsumptionDetailedInformationBinding
import com.example.sdgbachelorproject.data.model.HeatingConsumption
import com.example.sdgbachelorproject.view.MyApplication
import com.example.sdgbachelorproject.viewModel.ConsumptionsViewModel
import com.example.sdgbachelorproject.viewModel.SignInViewModel
import kotlinx.android.synthetic.main.heating_consumption_detailed_information.*
import kotlinx.android.synthetic.main.heating_consumption_detailed_information.view.*
import kotlinx.android.synthetic.main.view_add_consumption.view.*
import javax.inject.Inject


class HeatingConsumptionDetailedInformation : Fragment() {

    @Inject
    lateinit var signInViewModel: SignInViewModel

    @Inject
    lateinit var consumptionsViewModel: ConsumptionsViewModel

    private var _binding: HeatingConsumptionDetailedInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger
        (activity?.application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HeatingConsumptionDetailedInformationBinding.inflate(inflater, container, false)
        val view = binding.root

        val picker = view.heating_add_consumption_house_type.number_picker_detailed_consumption
        val pickerTitle = view.heating_add_consumption_house_type.txt_detailed_consumption

        val houseTypeArray = arrayOf(
            "House",
            "Small apartment",
            "Mid apartment",
            "Big apartment"
        )

        picker.setMinValue(0)
        picker.setMaxValue(3)
        picker.setDisplayedValues(
            houseTypeArray
        )

        // Set width of number picker programmatically
        val pickerParams: ViewGroup.LayoutParams = picker.layoutParams
        pickerParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        picker.layoutParams = pickerParams

        val titleParams: ViewGroup.LayoutParams = pickerTitle.layoutParams
        titleParams.width = 650
        pickerTitle.layoutParams = titleParams

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        btn_change_consumptions_heating.setOnClickListener {
            val temperature =
                view?.heating_add_consumption_temperature?.number_picker_detailed_consumption?.value
            val houseType =
                view?.heating_add_consumption_house_type?.number_picker_detailed_consumption?.value
            val numOfRooms =
                view?.heating_add_consumption_number_of_rooms?.number_picker_detailed_consumption?.value

            val userId = signInViewModel.currentUser.value?.uid

            val heatingConsumption =
                HeatingConsumption(temperature!!, houseType!!, numOfRooms!!, userId)

            if (userId != null) {
                consumptionsViewModel.postHeatingConsumptions(userId, heatingConsumption)
            }
            Toast.makeText(this.context, "Consumptions updated!", Toast.LENGTH_LONG).show()
        }
    }

}