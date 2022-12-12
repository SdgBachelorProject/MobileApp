package com.example.sdgbachelorproject.model.repositories

import com.example.sdgbachelorproject.model.api.ConsumptionsApi
import com.example.sdgbachelorproject.model.api.Retrofit
import com.example.sdgbachelorproject.model.data.User
import com.example.sdgbachelorproject.model.data.UserResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirebaseRepository {

    val currentUser = BehaviorSubject.create<FirebaseUser>()
    val currentUsersFirebaseToken = BehaviorSubject.create<GetTokenResult>()
    val allUsers = BehaviorSubject.create<List<User>>()

    fun postUserToDb(user: FirebaseUser) {
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        val convertedUser =
            user?.displayName?.let { user?.email?.let { it1 -> User(it1, user.uid, it, listOf()) } }

        if (convertedUser != null) {
            retrofit.postUserToDb(convertedUser).enqueue(
                object : Callback<User> {
                    override fun onResponse(
                        call: Call<User>,
                        response: Response<User>
                    ) {
                        println("ccc Success postUserToDb ${response.message()}")
                        println("ccc Success postUserToDb ${response}")
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        println("ccc Failure postUserToDb ${t.toString()}")
                    }

                }
            )
        }
    }

    fun getAllUsers() {
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.getAllUsers().enqueue(object : Callback<UserResult> {
            override fun onResponse(call: Call<UserResult>, response: Response<UserResult>) {
                println("ccc Success getAllUsers: ${response.body().toString()}")
                response.body()?.let { allUsers.onNext(it) }
            }

            override fun onFailure(call: Call<UserResult>, t: Throwable) {
                println("ccc Failure getAllUsers: ${t.toString()}")
            }
        })
    }

    fun updateUserToDb(user: User) {
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.updateUserFriendList(user.userId, user).enqueue(
            object : Callback<User> {
                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
                ) {
                    println("ccc Success updateUserToDb ${response.message()}")
                    println("ccc Success updateUserToDb ${response}")
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    println("ccc Failure updateUserToDb ${t.toString()}")
                }

            }
        )
    }
}