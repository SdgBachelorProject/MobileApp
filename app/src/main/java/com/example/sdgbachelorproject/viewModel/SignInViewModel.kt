package com.example.sdgbachelorproject.viewModel

import androidx.lifecycle.ViewModel
import com.example.sdgbachelorproject.model.data.User
import com.example.sdgbachelorproject.model.repositories.FirebaseRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository) :
    ViewModel() {

    val currentUser = firebaseRepository.currentUser
    val currentUsersFirebaseToken = firebaseRepository.currentUsersFirebaseToken

    val allUsers =
        firebaseRepository.allUsers

    val currentUserId = currentUser.value?.uid


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

    fun postUserToDb(user: FirebaseUser) {
        firebaseRepository.postUserToDb(user)
    }

    fun updateUserToDb(user: User) {
        firebaseRepository.updateUserToDb(user)
    }

    fun getAllUsers() {
        firebaseRepository.getAllUsers()
    }

//    fun removeFriend(usersFriends: MutableList<String>) {
//        val currentUserObject = allUsers.value?.filter {
//            it.userId == currentUser.value?.uid
//        }?.map {
//            User(it.userEmail, it.userId, it.username, usersFriends)
//        }?.firstOrNull()
//        println("ccc currentUserObject: ${currentUserObject.toString()}")
//        if (currentUserObject != null) {
//            updateUserToDb(currentUserObject)
//        }
//    }

    fun removeFriend(newUsersFriends: MutableList<String>) {
        val currentUser = allUsers.value?.find { it.userId == currentUser.value?.uid }
        val modifiedUserObject = newUsersFriends?.let { currentUser?.copy(userFriends = it) }

        if (modifiedUserObject != null) {
            updateUserToDb(modifiedUserObject)
        }
    }

//    fun addFriend(user: User) {
//        val oldFriendList = allUsers.value?.filter {
//            it.userId == currentUser.value?.uid
//        }?.map { it }?.map { it.userFriends }?.firstOrNull()
//
//        val newFriendList = mutableListOf<String>()
//        oldFriendList?.forEach {
//            newFriendList.add(it)
//        }
//        newFriendList.add(user.userId)
//
//
//        val modifiedUserObject = allUsers.value?.filter {
//            it.userId == currentUser.value?.uid
//        }?.map {
//            User(it.userEmail, it.userId, it.username, newFriendList)
//        }?.firstOrNull()
//        println("ccc currentUserObject: ${modifiedUserObject.toString()}")
//        if (modifiedUserObject != null) {
//            updateUserToDb(modifiedUserObject)
//        }
//    }

    fun addFriend(user: User) {
        val currentUser = allUsers.value?.find { it.userId == currentUser.value?.uid }
        val newFriendList = currentUser?.userFriends?.plus(user.userId)
        val modifiedUserObject = newFriendList?.let { currentUser?.copy(userFriends = it) }

        if (modifiedUserObject != null) {
            updateUserToDb(modifiedUserObject)
        }
    }
}