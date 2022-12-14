package com.example.sdgbachelorproject.model.data

data class AllQuizAnswersItem(
    val content: String,
    val correct: Boolean,
    val id: Int,
    val quiz: Int,
    val quiz_question: Int
)