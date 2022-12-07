package com.example.sdgbachelorproject.model.data

import com.google.gson.annotations.SerializedName

data class HeatingConsumption(
    @SerializedName("temperatureInYourHoushold") val temperature: Int,
    @SerializedName("buildingType") val houseType: Int,
    @SerializedName("numberOfRooms") val numOfRooms: Int,
    @SerializedName("user") val userUid: String?
)
