package com.example.sdgbachelorproject.viewModel

import androidx.lifecycle.ViewModel
import com.example.sdgbachelorproject.model.repositories.FirebaseRepository
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository) :
    ViewModel() {

    fun printToConsole() {
        println("ccc VM")
        firebaseRepository.consoleLogRepo()
    }
}