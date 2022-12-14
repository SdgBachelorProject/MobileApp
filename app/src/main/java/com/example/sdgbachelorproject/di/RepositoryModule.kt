package com.example.sdgbachelorproject.di

import com.example.sdgbachelorproject.data.repositories.ConsumptionsRepository
import com.example.sdgbachelorproject.data.repositories.UserRepository
import com.example.sdgbachelorproject.data.repositories.QuizesRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object RepositoryModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideFirebaseRepository(retrofit: Retrofit) = UserRepository(retrofit)

    @JvmStatic
    @Provides
    @Singleton
    fun provideConsumptionsRepository(retrofit: Retrofit) = ConsumptionsRepository(retrofit)

    @JvmStatic
    @Provides
    @Singleton
    fun provideQuizesRepository(retrofit: Retrofit) = QuizesRepository(retrofit)

}