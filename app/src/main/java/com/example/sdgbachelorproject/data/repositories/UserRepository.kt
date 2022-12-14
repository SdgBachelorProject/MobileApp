package com.example.sdgbachelorproject.data.repositories

import com.example.sdgbachelorproject.data.api.ConsumptionsApi
import com.example.sdgbachelorproject.data.api.Retrofit
import com.example.sdgbachelorproject.data.model.User
import com.example.sdgbachelorproject.data.model.UserResult
import com.example.sdgbachelorproject.utils.behavior
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    val currentUser = behavior<FirebaseUser>()
    val currentUsersFirebaseToken = behavior<GetTokenResult>()
    val allUsers = behavior<List<User>>()
    val currentUserFriends = behavior<UserResult>()

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

    fun getUserFriends(userId: String) {
        val retrofit = Retrofit.buildService(ConsumptionsApi::class.java)

        retrofit.getUserFriends(userId).enqueue(object : Callback<UserResult> {
            override fun onResponse(call: Call<UserResult>, response: Response<UserResult>) {
                println("ccc Success getUserFriends: ${response.body().toString()}")
                response.body()?.let { currentUserFriends.onNext(it) }
            }

            override fun onFailure(call: Call<UserResult>, t: Throwable) {
                println("ccc Failure getUserFriends: ${t.toString()}")
            }
        })
    }
}