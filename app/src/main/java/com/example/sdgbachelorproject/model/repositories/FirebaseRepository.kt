package com.example.sdgbachelorproject.model.repositories

import io.reactivex.subjects.BehaviorSubject

class FirebaseRepository {

    val test = BehaviorSubject.createDefault(true)

    fun consoleLogRepo() {
        println("ccc REPO")
    }
}