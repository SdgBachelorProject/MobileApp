package com.example.sdgbachelorproject.data.repositories

import com.example.sdgbachelorproject.data.api.ConsumptionsApi
import com.example.sdgbachelorproject.data.api.Retrofit
import com.example.sdgbachelorproject.data.model.*
import com.example.sdgbachelorproject.utils.behavior
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsumptionsRepository {

    val calculatedWaterConsumption = behavior<List<WaterResultItem>>()
    val calculatedHeatingConsumption = behavior<List<HeatingResultItem>>()
    val calculatedElectricityConsumption = behavior<List<ElectricityResultItem>>()

    fun postElectricityConsumptions(
        userId: String,
        electricityConsumption: ElectricityConsumption
    ) {
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.addElectricityConsumptions(electricityConsumption).enqueue(
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
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.updateElectricityConsumption(userId, electricityConsumption).enqueue(
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
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.addHeatingConsumptions(heatingConsumption).enqueue(
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
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.updateHeatingConsumption(userId, heatingConsumption).enqueue(
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
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.addWaterConsumptions(waterConsumption).enqueue(
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
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.updateWaterConsumption(userId, waterConsumption).enqueue(
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
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.getWaterConsumption().enqueue(object : Callback<WaterResult> {
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
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.getHeatingConsumption().enqueue(object : Callback<HeatingResult> {
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
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.getElectricityConsumption().enqueue(object : Callback<ElectricityResult> {
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