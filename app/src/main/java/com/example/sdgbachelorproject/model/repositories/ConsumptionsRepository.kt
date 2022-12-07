package com.example.sdgbachelorproject.model.repositories

import com.example.sdgbachelorproject.model.api.ConsumptionsApi
import com.example.sdgbachelorproject.model.api.Retrofit
import com.example.sdgbachelorproject.model.data.*
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsumptionsRepository {

    val calculatedWaterConsumption = BehaviorSubject.create<Int>()
    val calculatedHeatingConsumption = BehaviorSubject.create<Int>()

    fun postElectricityConsumptions(electricityConsumption: ElectricityConsumption) {
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.addElectricityConsumptions(electricityConsumption).enqueue(
            object : Callback<ElectricityConsumption> {
                override fun onResponse(
                    call: Call<ElectricityConsumption>,
                    response: Response<ElectricityConsumption>
                ) {
                    println("ccc SUCCESS${response.message()}")
                    println("ccc SUCCESS${response}")
                }

                override fun onFailure(call: Call<ElectricityConsumption>, t: Throwable) {
                    println("ccc Failure ${t.toString()}")
                }

            }
        )
    }

    fun postHeatingConsumptions(heatingConsumption: HeatingConsumption) {
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.addHeatingConsumptions(heatingConsumption).enqueue(
            object : Callback<HeatingConsumption> {
                override fun onResponse(
                    call: Call<HeatingConsumption>,
                    response: Response<HeatingConsumption>
                ) {
                    println("ccc SUCCESS${response.message()}")
                    println("ccc SUCCESS${response}")
                }

                override fun onFailure(call: Call<HeatingConsumption>, t: Throwable) {
                    println("ccc Failure ${t.toString()}")
                }

            }
        )
    }

    fun postWaterConsumptions(waterConsumption: WaterConsumption) {
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.addWaterConsumptions(waterConsumption).enqueue(
            object : Callback<WaterConsumption> {
                override fun onResponse(
                    call: Call<WaterConsumption>,
                    response: Response<WaterConsumption>
                ) {
                    println("ccc SUCCESS${response.message()}")
                    println("ccc SUCCESS${response}")
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
                response.body()?.last()?.waterConsumption?.let {
                    calculatedWaterConsumption.onNext(
                        it
                    )
                }
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
                response.body()?.last()?.heatingConsumption?.let {
                    calculatedHeatingConsumption.onNext(
                        it
                    )
                }
            }

            override fun onFailure(call: Call<HeatingResult>, t: Throwable) {
                println("ccc Failure GET")
            }
        })
    }
}