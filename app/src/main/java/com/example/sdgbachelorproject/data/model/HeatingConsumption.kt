package com.example.sdgbachelorproject.data.model

import com.google.gson.annotations.SerializedName

data class HeatingConsumption(
    @SerializedName("temperatureInYourHoushold") val temperature: Int,
    @SerializedName("buildingType") val houseType: Int,
    @SerializedName("numberOfRooms") val numOfRooms: Int,
    @SerializedName("user") val userUid: String?
)
