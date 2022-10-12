package com.example.sdgbachelorproject.viewModel

import androidx.lifecycle.ViewModel
import com.example.sdgbachelorproject.model.repositories.FirebaseRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository) :
    ViewModel() {

    val isTestEnabled = firebaseRepository.test
    val currentUser = firebaseRepository.currentUser

    fun printToConsole() {
        println("ccc VM: ${isTestEnabled.value}")
        firebaseRepository.consoleLogRepo()
    }

    fun getCurrentUser(user: FirebaseUser) {
        currentUser.onNext(user)
    }
}