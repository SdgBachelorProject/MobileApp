package com.example.sdgbachelorproject.data.repositories

import com.example.sdgbachelorproject.data.api.ConsumptionsApi
import com.example.sdgbachelorproject.data.model.*
import com.example.sdgbachelorproject.utils.behavior
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ConsumptionsRepository @Inject constructor(private val retrofit: retrofit2.Retrofit) {

    val retrofitInstance = retrofit.create(ConsumptionsApi::class.java)

    val calculatedWaterConsumption = behavior<List<WaterResultItem>>()
    val calculatedHeatingConsumption = behavior<List<HeatingResultItem>>()
    val calculatedElectricityConsumption = behavior<List<ElectricityResultItem>>()

    fun postElectricityConsumptions(
        userId: String,
        electricityConsumption: ElectricityConsumption
    ) {

        retrofitInstance.addElectricityConsumptions(electricityConsumption).enqueue(
            object : Callback<ElectricityConsumption> {
                override fun onResponse(
                    call: Call<ElectricityConsumption>,
                    response: Response<ElectricityConsumption>
                ) {
                    println("ccc SUCCESS${response.message()}")
                    println("ccc SUCCESS${response}")
                    if (response.code() == 400) {
                        println("ccc data already added executing PUT")
                        updateElectricityConsumptions(userId, electricityConsumption)
                    }
                }

                override fun onFailure(call: Call<ElectricityConsumption>, t: Throwable) {
                    println("ccc Failure ${t.toString()}")
                }

            }
        )
    }

    fun updateElectricityConsumptions(
        userId: String,
        electricityConsumption: ElectricityConsumption
    ) {

        retrofitInstance.updateElectricityConsumption(userId, electricityConsumption).enqueue(
            object : Callback<ElectricityConsumption> {
                override fun onResponse(
                    call: Call<ElectricityConsumption>,
                    response: Response<ElectricityConsumption>
                ) {
                    println("ccc SUCCESS ${response.message()}")
                    println("ccc SUCCESS ${response}")
                }

                override fun onFailure(call: Call<ElectricityConsumption>, t: Throwable) {
                    println("ccc Failure ${t.toString()}")
                }

            }
        )
    }

    fun postHeatingConsumptions(userId: String, heatingConsumption: HeatingConsumption) {

        retrofitInstance.addHeatingConsumptions(heatingConsumption).enqueue(
            object : Callback<HeatingConsumption> {
                override fun onResponse(
                    call: Call<HeatingConsumption>,
                    response: Response<HeatingConsumption>
                ) {
                    println("ccc SUCCESS${response.message()}")
                    println("ccc SUCCESS${response}")
                    if (response.code() == 400) {
                        println("ccc data already added executing PUT")
                        updateHeatingConsumptions(userId, heatingConsumption)
                    }
                }

                override fun onFailure(call: Call<HeatingConsumption>, t: Throwable) {
                    println("ccc Failure ${t.toString()}")
                }

            }
        )
    }

    fun updateHeatingConsumptions(userId: String, heatingConsumption: HeatingConsumption) {

        retrofitInstance.updateHeatingConsumption(userId, heatingConsumption).enqueue(
            object : Callback<HeatingConsumption> {
                override fun onResponse(
                    call: Call<HeatingConsumption>,
                    response: Response<HeatingConsumption>
                ) {
                    println("ccc SUCCESS ${response.message()}")
                    println("ccc SUCCESS ${response}")
                }

                override fun onFailure(call: Call<HeatingConsumption>, t: Throwable) {
                    println("ccc Failure ${t.toString()}")
                }

            }
        )
    }

    fun postWaterConsumptions(userId: String, waterConsumption: WaterConsumption) {

        retrofitInstance.addWaterConsumptions(waterConsumption).enqueue(
            object : Callback<WaterConsumption> {
                override fun onResponse(
                    call: Call<WaterConsumption>,
                    response: Response<WaterConsumption>
                ) {
                    println("ccc SUCCESS${response.message()}")
                    println("ccc SUCCESS${response}")
                    if (response.code() == 400) {
                        println("ccc data already added executing PUT")
                        updateWaterConsumptions(userId, waterConsumption)
                    }
                }

                override fun onFailure(call: Call<WaterConsumption>, t: Throwable) {
                    println("ccc Failure ${t.toString()}")
                }

            }
        )
    }

    fun updateWaterConsumptions(userId: String, waterConsumption: WaterConsumption) {

        retrofitInstance.updateWaterConsumption(userId, waterConsumption).enqueue(
            object : Callback<WaterConsumption> {
                override fun onResponse(
                    call: Call<WaterConsumption>,
                    response: Response<WaterConsumption>
                ) {
                    println("ccc SUCCESS ${response.message()}")
                    println("ccc SUCCESS ${response}")
                }

                override fun onFailure(call: Call<WaterConsumption>, t: Throwable) {
                    println("ccc Failure ${t.toString()}")
                }

            }
        )
    }

    fun getWaterConsumption() {

        retrofitInstance.getWaterConsumption().enqueue(object : Callback<WaterResult> {
            override fun onResponse(call: Call<WaterResult>, response: Response<WaterResult>) {
                response.body()?.let { calculatedWaterConsumption.onNext(it.toList()) }
                println("ccc SUCC GET: ${response.body().toString()}")
            }

            override fun onFailure(call: Call<WaterResult>, t: Throwable) {
                println("ccc Failure GET")
            }

        })
    }

    fun getHeatingConsumption() {

        retrofitInstance.getHeatingConsumption().enqueue(object : Callback<HeatingResult> {
            override fun onResponse(call: Call<HeatingResult>, response: Response<HeatingResult>) {
                println("ccc SUCC GET: ${response.body().toString()}")
                response.body()?.let { calculatedHeatingConsumption.onNext(it.toList()) }
            }

            override fun onFailure(call: Call<HeatingResult>, t: Throwable) {
                println("ccc Failure GET: ${t.toString()}")
            }
        })
    }

    fun getElectricityConsumption() {

        retrofitInstance.getElectricityConsumption().enqueue(object : Callback<ElectricityResult> {
            override fun onResponse(
                call: Call<ElectricityResult>,
                response: Response<ElectricityResult>
            ) {
                println("ccc SUCC GET: ${response.body().toString()}")
                response.body()?.let { calculatedElectricityConsumption.onNext(it.toList()) }
            }

            override fun onFailure(call: Call<ElectricityResult>, t: Throwable) {
                println("ccc Failure GET: ${t.toString()}")
            }
        })
    }
}