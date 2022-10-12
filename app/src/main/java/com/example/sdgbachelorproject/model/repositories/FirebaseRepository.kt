package com.example.sdgbachelorproject.model.repositories

import com.google.firebase.auth.FirebaseUser
import io.reactivex.subjects.BehaviorSubject

class FirebaseRepository {

    val test = BehaviorSubject.createDefault(true)
    val currentUser = BehaviorSubject.create<FirebaseUser>()

    fun consoleLogRepo() {
        println("ccc REPO")
    }
}