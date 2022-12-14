package com.example.sdgbachelorproject.data.model

data class ElectricityResultItem(
    val electricityConsumption: String,
    val hoursOfComputerUsage: Int,
    val hoursOfPhoneUsage: Int,
    val hoursOfTVUsage: Int,
    val id: Int,
    val user: String
)