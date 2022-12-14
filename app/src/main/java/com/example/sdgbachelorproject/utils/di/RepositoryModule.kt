package com.example.sdgbachelorproject.utils.di

import com.example.sdgbachelorproject.model.repositories.ConsumptionsRepository
import com.example.sdgbachelorproject.model.repositories.FirebaseRepository
import com.example.sdgbachelorproject.model.repositories.QuizesRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object RepositoryModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideFirebaseRepository() = FirebaseRepository()

    @JvmStatic
    @Provides
    @Singleton
    fun provideConsumptionsRepository() = ConsumptionsRepository()

    @JvmStatic
    @Provides
    @Singleton
    fun provideQuizesRepository(retrofit: Retrofit) = QuizesRepository(retrofit)

}