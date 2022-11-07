package com.example.sdgbachelorproject.utils.di

import android.content.Context
import com.example.sdgbachelorproject.FirstFragment
import com.example.sdgbachelorproject.MainActivity
import com.google.firebase.quickstart.auth.kotlin.SignInActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// Scope annotation that the AppComponent uses
// Classes annotated with @Singleton will have a unique instance in this Component
@Singleton
// Definition of a Dagger component that adds info from the different modules to the graph
@Component(modules = [RepositoryModule::class])
interface AppComponent {
    // Classes that can be injected by this Component
    fun inject(activity: SignInActivity)
    fun inject(activity: MainActivity)
    fun inject(fragment: FirstFragment)

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }
}