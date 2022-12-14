package com.example.sdgbachelorproject.model.api

import com.example.sdgbachelorproject.model.data.*
import retrofit2.Call
import retrofit2.http.*

interface ConsumptionsApi {
    @Headers("Content-Type: application/json")
    @POST("electricity/create/")
    fun addElectricityConsumptions(@Body electricityConsumption: ElectricityConsumption): Call<ElectricityConsumption>

    @Headers("Content-Type: application/json")
    @PUT("electricity/{userId}/update/")
    fun updateElectricityConsumption(
        @Path("userId") userId: String,
        @Body electricityConsumption: ElectricityConsumption
    ): Call<ElectricityConsumption>

    @Headers("Content-Type: application/json")
    @POST("heating/create/")
    fun addHeatingConsumptions(@Body heatingConsumption: HeatingConsumption): Call<HeatingConsumption>

    @Headers("Content-Type: application/json")
    @PUT("heating/{userId}/update/")
    fun updateHeatingConsumption(
        @Path("userId") userId: String,
        @Body heatingConsumption: HeatingConsumption
    ): Call<HeatingConsumption>

    @Headers("Content-Type: application/json")
    @POST("water/create/")
    fun addWaterConsumptions(@Body waterConsumption: WaterConsumption): Call<WaterConsumption>

    @Headers("Content-Type: application/json")
    @PUT("water/{userId}/update/")
    fun updateWaterConsumption(
        @Path("userId") userId: String,
        @Body waterConsumption: WaterConsumption
    ): Call<WaterConsumption>

    @GET("allwater")
    fun getWaterConsumption(): Call<WaterResult>

    @GET("allheatings")
    fun getHeatingConsumption(): Call<HeatingResult>

    @GET("allelectricities")
    fun getElectricityConsumption(): Call<ElectricityResult>

    @Headers("Content-Type: application/json")
    @POST("user/create/")
    fun postUserToDb(@Body user: User): Call<User>

    @Headers("Content-Type: application/json")
    @PUT("user/{userId}/update/")
    fun updateUserFriendList(@Path("userId") userId: String, @Body user: User): Call<User>

    @GET("allusers")
    fun getAllUsers(): Call<UserResult>

    @GET("user/{userId}/friends")
    fun getUserFriends(@Path("userId") userId: String): Call<UserResult>

    @GET("allquizquestions/")
    fun getAllQuizQuestions(): Call<AllQuizQuestions>

    @GET("allquizes/")
    fun getAllQuiz(): Call<AllQuiz>

    @GET("allquizanswers/")
    fun getAllQuizAnswers(): Call<AllQuizAnswers>
}