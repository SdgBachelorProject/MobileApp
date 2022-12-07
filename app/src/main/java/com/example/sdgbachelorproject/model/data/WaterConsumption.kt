package com.example.sdgbachelorproject.model.data

import com.google.gson.annotations.SerializedName

data class WaterConsumption(
    @SerializedName("bathsPerWeek") val bath: Int,
    @SerializedName("numberOfShowerPerWeek") val showerAmount: Int,
    @SerializedName("singleShowerDuration") val showerTime: Int,
    @SerializedName("hasDishwasher") val dishesQuestion: Boolean,
    @SerializedName("numberOfDishwashesPerWeek") val dishesAmount: Int,
    @SerializedName("numberOfWashingMachineUsage") val washingQuestion: Int,
    @SerializedName("user") val userUid: String?
)
