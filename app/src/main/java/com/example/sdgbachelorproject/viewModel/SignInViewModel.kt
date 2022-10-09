package com.example.sdgbachelorproject.viewModel

import androidx.lifecycle.ViewModel
import com.example.sdgbachelorproject.model.repositories.FirebaseRepository
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository) :
    ViewModel() {

    val isTestEnabled = firebaseRepository.test

    fun printToConsole() {
        println("ccc VM: ${isTestEnabled.value}")
        firebaseRepository.consoleLogRepo()
    }
}