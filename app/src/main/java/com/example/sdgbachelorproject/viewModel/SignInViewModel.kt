package com.example.sdgbachelorproject.viewModel

import androidx.lifecycle.ViewModel
import com.example.sdgbachelorproject.model.repositories.FirebaseRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult
import io.reactivex.annotations.NonNull
import io.reactivex.internal.util.HalfSerializer.onComplete
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository) :
    ViewModel() {

    val isTestEnabled = firebaseRepository.test
    val currentUser = firebaseRepository.currentUser
    val currentUsersFirebaseToken = firebaseRepository.currentUsersFirebaseToken

    fun printToConsole() {
        println("ccc VM: ${isTestEnabled.value}")
        firebaseRepository.consoleLogRepo()
    }

    fun getCurrentUser(user: FirebaseUser) {
        currentUser.onNext(user)
    }

    fun getCurrentUsersFirebaseToken(user: FirebaseUser) {
        user.getIdToken(true).addOnCompleteListener(OnCompleteListener {
            if (it.isSuccessful) {
                currentUsersFirebaseToken.onNext(it.result)
            }
        })
    }
}