package com.example.sdgbachelorproject.data.model

data class WaterResultItem(
    val bathsPerWeek: Int,
    val hasDishwasher: Boolean,
    val id: Int,
    val numberOfDishwashesPerWeek: Int,
    val numberOfShowerPerWeek: Int,
    val numberOfWashingMachineUsage: Int,
    val singleShowerDuration: Int,
    val waterConsumption: Float,
    val user: String
)