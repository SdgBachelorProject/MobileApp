package com.example.sdgbachelorproject.viewModel

import androidx.lifecycle.ViewModel
import com.example.sdgbachelorproject.data.model.User
import com.example.sdgbachelorproject.data.repositories.UserRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val firebaseRepository: UserRepository) :
    ViewModel() {

    val currentUser = firebaseRepository.currentUser
    val currentUsersFirebaseToken = firebaseRepository.currentUsersFirebaseToken
    val currentUserFriends = firebaseRepository.currentUserFriends

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

    fun getUserFriends() {
        if (currentUserId != null) {
            firebaseRepository.getUserFriends(currentUserId)
        }
    }

    fun removeFriend(newUsersFriends: MutableList<String>) {
        val currentUser = allUsers.value?.find { it.userId == currentUser.value?.uid }
        val modifiedUserObject = newUsersFriends?.let { currentUser?.copy(userFriends = it) }

        if (modifiedUserObject != null) {
            updateUserToDb(modifiedUserObject)
        }
    }

    fun addFriend(user: User) {
        val currentUser = allUsers.value?.find { it.userId == currentUser.value?.uid }
        val newFriendList = currentUser?.userFriends?.plus(user.userId)
        val modifiedUserObject = newFriendList?.let { currentUser?.copy(userFriends = it) }

        if (modifiedUserObject != null) {
            updateUserToDb(modifiedUserObject)
        }
    }
}