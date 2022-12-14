package com.example.sdgbachelorproject.di

import android.content.Context
import com.example.sdgbachelorproject.data.repositories.QuizesRepository
import com.example.sdgbachelorproject.view.*
import com.example.sdgbachelorproject.view.detailedConsumptionViews.ElectricityConsumptionDetailedInformation
import com.example.sdgbachelorproject.view.detailedConsumptionViews.HeatingConsumptionDetailedInformation
import com.example.sdgbachelorproject.view.detailedConsumptionViews.WaterConsumptionDetailedInformation
import com.example.sdgbachelorproject.view.lessons.electricity.lesson1.Path1Quiz
import com.google.firebase.quickstart.auth.kotlin.SignInActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// Scope annotation that the AppComponent uses
// Classes annotated with @Singleton will have a unique instance in this Component
@Singleton
// Definition of a Dagger component that adds info from the different modules to the graph
@Component(modules = [RepositoryModule::class, RetrofitModule::class])
interface AppComponent {
    // Classes that can be injected by this Component
    fun inject(activity: SignInActivity)
    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: FriendsFragment)
    fun inject(fragment: TutorialFragment)
    fun inject(fragment: Path1Quiz)
    fun inject(fragment: FriendsAddFragment)
    fun inject(fragment: SettingsFragment)
    fun inject(fragment: WaterConsumptionDetailedInformation)
    fun inject(fragment: ElectricityConsumptionDetailedInformation)
    fun inject(fragment: HeatingConsumptionDetailedInformation)
    fun inject(quizesRepository: QuizesRepository)

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context, retrofitModule: RetrofitModule): AppComponent
    }
}