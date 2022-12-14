package com.example.sdgbachelorproject.viewModel

import androidx.lifecycle.ViewModel
import com.example.sdgbachelorproject.data.repositories.QuizesRepository
import javax.inject.Inject

class QuizViewModel @Inject constructor(private val quizesRepository: QuizesRepository) :
    ViewModel() {

    val allQuizQuestions = quizesRepository.allQuizQuestions
    val allQuizAnswers = quizesRepository.allQuizAnswers

    fun getAllQuizQuestions() {
        quizesRepository.getAllQuizQuestions()
    }

    fun getAllQuizAnswers() {
        quizesRepository.getAllQuizAnswers()
    }
}