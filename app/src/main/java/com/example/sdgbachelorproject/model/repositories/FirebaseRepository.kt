package com.example.sdgbachelorproject.model.repositories

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult
import io.reactivex.subjects.BehaviorSubject

class FirebaseRepository {

    val test = BehaviorSubject.createDefault(true)
    val currentUser = BehaviorSubject.create<FirebaseUser>()
    val currentUsersFirebaseToken = BehaviorSubject.create<GetTokenResult>()

    fun consoleLogRepo() {
        println("ccc REPO")
    }
}