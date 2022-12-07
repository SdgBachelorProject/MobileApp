package com.example.sdgbachelorproject.model.api

import com.example.sdgbachelorproject.model.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ConsumptionsApi {
    @Headers("Content-Type: application/json")
    @POST("electricity/create/")
    fun addElectricityConsumptions(@Body electricityConsumption: ElectricityConsumption): Call<ElectricityConsumption>

    @Headers("Content-Type: application/json")
    @POST("heating/create/")
    fun addHeatingConsumptions(@Body heatingConsumption: HeatingConsumption): Call<HeatingConsumption>

    @Headers("Content-Type: application/json")
    @POST("water/create/")
    fun addWaterConsumptions(@Body waterConsumption: WaterConsumption): Call<WaterConsumption>

    @GET("allwater")
    fun getWaterConsumption(): Call<WaterResult>

    @GET("allheatings")
    fun getHeatingConsumption(): Call<HeatingResult>
}