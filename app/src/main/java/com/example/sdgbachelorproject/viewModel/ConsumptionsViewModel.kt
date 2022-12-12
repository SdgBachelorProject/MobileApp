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
    val calculatedElectricityConsumption = consumptionsRepository.calculatedElectricityConsumption

    fun postElectricityConsumptions(userId: String, electricityConsumption: ElectricityConsumption) {
        consumptionsRepository.postElectricityConsumptions(userId, electricityConsumption)
    }

    fun postHeatingConsumptions(userId: String, heatingConsumption: HeatingConsumption) {
        consumptionsRepository.postHeatingConsumptions(userId, heatingConsumption)
    }

    fun postWaterConsumptions(userId: String, waterConsumption: WaterConsumption) {
        consumptionsRepository.postWaterConsumptions(userId, waterConsumption)
    }

    fun getWaterConsumption() {
        consumptionsRepository.getWaterConsumption()
    }

    fun getHeatingConsumption() {
        consumptionsRepository.getHeatingConsumption()
    }

    fun getElectricityConsumption() {
        consumptionsRepository.getElectricityConsumption()
    }
}