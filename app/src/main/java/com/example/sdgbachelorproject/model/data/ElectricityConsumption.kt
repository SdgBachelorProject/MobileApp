package com.example.sdgbachelorproject.model.data

import com.google.gson.annotations.SerializedName

data class ElectricityConsumption(
    @SerializedName("hoursOfPhoneUsage") val phoneHours: Int,
    @SerializedName("hoursOfTVUsage") val tvHours: Int,
    @SerializedName("hoursOfComputerUsage") val pcHours: Int,
    @SerializedName("user") val userUid: String?
)
