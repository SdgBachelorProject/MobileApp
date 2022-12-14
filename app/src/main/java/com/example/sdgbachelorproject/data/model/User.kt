package com.example.sdgbachelorproject.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email") val userEmail: String,
    @SerializedName("uid") val userId: String,
    @SerializedName("username") val username: String,
    @SerializedName("friends") val userFriends: List<String>,
)