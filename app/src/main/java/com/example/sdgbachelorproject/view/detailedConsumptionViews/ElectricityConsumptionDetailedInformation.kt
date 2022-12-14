package com.example.sdgbachelorproject.view.detailedConsumptionViews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sdgbachelorproject.databinding.ElectricityConsumptionDetailedInformationBinding
import com.example.sdgbachelorproject.data.model.ElectricityConsumption
import com.example.sdgbachelorproject.view.MyApplication
import com.example.sdgbachelorproject.viewModel.ConsumptionsViewModel
import com.example.sdgbachelorproject.viewModel.SignInViewModel
import kotlinx.android.synthetic.main.electricity_consumption_detailed_information.*
import kotlinx.android.synthetic.main.electricity_consumption_detailed_information.view.*
import kotlinx.android.synthetic.main.view_add_consumption.view.*
import javax.inject.Inject

class ElectricityConsumptionDetailedInformation : Fragment() {

    @Inject
    lateinit var signInViewModel: SignInViewModel

    @Inject
    lateinit var consumptionsViewModel: ConsumptionsViewModel

    private var _binding: ElectricityConsumptionDetailedInformationBinding? = null
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
        _binding =
            ElectricityConsumptionDetailedInformationBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        btn_change_consumptions_electricity.setOnClickListener {
            val phoneHours =
                view?.electricity_add_consumption_phone?.number_picker_detailed_consumption?.value
            val tvHours =
                view?.electricity_add_consumption_tv?.number_picker_detailed_consumption?.value
            val pcHours =
                view?.electricity_add_consumption_pc?.number_picker_detailed_consumption?.value
            val userId = signInViewModel.currentUser.value?.uid

            val electricityConsumption =
                ElectricityConsumption(phoneHours!!, tvHours!!, pcHours!!, userId)
            if (userId != null) {
                consumptionsViewModel.postElectricityConsumptions(userId, electricityConsumption)
            }
            Toast.makeText(this.context, "Consumptions updated!", Toast.LENGTH_LONG).show()
        }
    }
}