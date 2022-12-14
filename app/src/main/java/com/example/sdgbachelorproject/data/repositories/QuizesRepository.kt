package com.example.sdgbachelorproject.data.repositories

import com.example.sdgbachelorproject.data.api.ConsumptionsApi
import com.example.sdgbachelorproject.data.model.AllQuizAnswers
import com.example.sdgbachelorproject.data.model.AllQuizAnswersItem
import com.example.sdgbachelorproject.data.model.AllQuizQuestions
import com.example.sdgbachelorproject.data.model.AllQuizQuestionsItem
import com.example.sdgbachelorproject.utils.behavior
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class QuizesRepository @Inject constructor(private val retrofit: Retrofit) {
    val retrofitInstance = retrofit.create(ConsumptionsApi::class.java)
    val allQuizQuestions = behavior<List<AllQuizQuestionsItem>>()

    //    val allQuizQuestions = behavior<AllQuizQuestions>()
    val allQuizAnswers = behavior<List<AllQuizAnswersItem>>()

    fun getAllQuizQuestions() {

        retrofitInstance.getAllQuizQuestions().enqueue(object : Callback<AllQuizQuestions> {
            override fun onResponse(
                call: Call<AllQuizQuestions>,
                response: Response<AllQuizQuestions>
            ) {
                response.body()?.let { allQuizQuestions.onNext(it) }
                println("ccc Success getAllQuizQuestions: ${response.body()?.size}")
            }

            override fun onFailure(call: Call<AllQuizQuestions>, t: Throwable) {
                println("ccc Failure getAllQuizQuestions ${t.toString()}")
            }

        })
    }

    fun getAllQuizAnswers() {

        retrofitInstance.getAllQuizAnswers().enqueue(object : Callback<AllQuizAnswers> {
            override fun onResponse(
                call: Call<AllQuizAnswers>,
                response: Response<AllQuizAnswers>
            ) {
                response.body()?.let { allQuizAnswers.onNext(it) }
                println("ccc Success getAllQuizAnswers: ${response.body()?.size}")
            }

            override fun onFailure(call: Call<AllQuizAnswers>, t: Throwable) {
                println("ccc Failure getAllQuizAnswers ${t.toString()}")
            }

        })
    }
}