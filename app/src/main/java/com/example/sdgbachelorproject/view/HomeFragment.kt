package com.example.sdgbachelorproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sdgbachelorproject.R
import com.example.sdgbachelorproject.databinding.FragmentHomeBinding
import com.example.sdgbachelorproject.utils.createAlertDialog
import com.example.sdgbachelorproject.utils.observeAsLiveData
import com.example.sdgbachelorproject.utils.switchFragment
import com.example.sdgbachelorproject.viewModel.ConsumptionsViewModel
import com.example.sdgbachelorproject.viewModel.SignInViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_current_consumption.view.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var signInViewModel: SignInViewModel

    @Inject
    lateinit var consumptionsViewModel: ConsumptionsViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger
        (activity?.application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        getCalculatedConsumptions()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
//        Electricity
        electricityConsumptionPanel.btn_add_consumption.setOnClickListener {
            switchFragment(R.id.electricityConsumptionDetailedInformation)
        }

        electricityConsumptionPanel.consumption_value_info.setOnClickListener {
            createAlertDialog("Electricity", this.requireContext())
        }

        consumptionsViewModel.calculatedElectricityConsumption.observeAsLiveData(viewLifecycleOwner) {
            it.forEach {
                if (it.user == signInViewModel.currentUser.value?.uid) {
                    electricityConsumptionPanel.consumption_value.text =
                        it.electricityConsumption + " kWh"
                }
            }
        }

//        Heating
        heatingConsumptionPanel.btn_add_consumption.setOnClickListener {
            switchFragment(R.id.heatingConsumptionDetailedInformation)
        }

        heatingConsumptionPanel.consumption_value_info.setOnClickListener {
            createAlertDialog("Heating", this.requireContext())
        }

        consumptionsViewModel.calculatedHeatingConsumption.observeAsLiveData(viewLifecycleOwner) {
            it.forEach {
                if (it.user == signInViewModel.currentUser.value?.uid) {
                    heatingConsumptionPanel.consumption_value.text =
                        it.heatingConsumption.toString() + " kWh"
                }
            }
        }

//        Water
        waterConsumptionPanel.btn_add_consumption.setOnClickListener {
            switchFragment(R.id.waterConsumptionDetailedInformation)
        }

        waterConsumptionPanel.consumption_value_info.setOnClickListener {
            createAlertDialog("Water", this.requireContext())
        }

        consumptionsViewModel.calculatedWaterConsumption.observeAsLiveData(viewLifecycleOwner) {
            it.forEach {
                if (it.user == signInViewModel.currentUser.value?.uid) {
                    waterConsumptionPanel.consumption_value.text =
                        it.waterConsumption.toString() + " liters"
                }
            }
        }
    }

    private fun getCalculatedConsumptions() {
        consumptionsViewModel.getWaterConsumption()
        consumptionsViewModel.getHeatingConsumption()
        consumptionsViewModel.getElectricityConsumption()
    }
}