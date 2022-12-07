package com.example.sdgbachelorproject.viewModel

import androidx.lifecycle.ViewModel
import com.example.sdgbachelorproject.model.data.ElectricityConsumption
import com.example.sdgbachelorproject.model.data.HeatingConsumption
import com.example.sdgbachelorproject.model.data.WaterConsumption
import com.example.sdgbachelorproject.model.repositories.ConsumptionsRepository
import javax.inject.Inject

class ConsumptionsViewModel @Inject constructor(private val consumptionsRepository: ConsumptionsRepository) :
    ViewModel() {

    val calculatedWaterConsumption = consumptionsRepository.calculatedWaterConsumption
    val calculatedHeatingConsumption = consumptionsRepository.calculatedHeatingConsumption

    fun postElectricityConsumptions(electricityConsumption: ElectricityConsumption) {
        consumptionsRepository.postElectricityConsumptions(electricityConsumption)
    }

    fun postHeatingConsumptions(heatingConsumption: HeatingConsumption) {
        consumptionsRepository.postHeatingConsumptions(heatingConsumption)
    }

    fun postWaterConsumptions(waterConsumption: WaterConsumption) {
        consumptionsRepository.postWaterConsumptions(waterConsumption)
    }

    fun getWaterConsumption() {
        consumptionsRepository.getWaterConsumption()
    }

    fun getHeatingConsumption() {
        consumptionsRepository.getHeatingConsumption()
    }
}