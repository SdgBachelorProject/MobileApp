package com.example.sdgbachelorproject.model.data

data class WaterResultItem(
    val bathsPerWeek: Int,
    val hasDishwasher: Boolean,
    val id: Int,
    val numberOfDishwashesPerWeek: Int,
    val numberOfShowerPerWeek: Int,
    val numberOfWashingMachineUsage: Int,
    val singleShowerDuration: Int,
    val waterConsumption: Int
)