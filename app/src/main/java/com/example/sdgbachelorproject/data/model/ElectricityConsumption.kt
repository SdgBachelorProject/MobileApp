package com.example.sdgbachelorproject.data.model

import com.google.gson.annotations.SerializedName

data class ElectricityConsumption(
    @SerializedName("hoursOfPhoneUsage") val phoneHours: Int,
    @SerializedName("hoursOfTVUsage") val tvHours: Int,
    @SerializedName("hoursOfComputerUsage") val pcHours: Int,
    @SerializedName("user") val userUid: String?
)
