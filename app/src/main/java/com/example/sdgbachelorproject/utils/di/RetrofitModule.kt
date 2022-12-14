package com.example.sdgbachelorproject.utils.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {
    private val client = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://sdgbackendapp.azurewebsites.net/BackednApp/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}
