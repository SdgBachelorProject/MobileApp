package com.example.sdgbachelorproject.view

import android.app.Application
import com.example.sdgbachelorproject.di.AppComponent
import com.example.sdgbachelorproject.di.RetrofitModule
import com.example.sdgbachelorproject.di.DaggerAppComponent


class MyApplication: Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        // Create an instance of the RetrofitModule class
        val retrofitModule = RetrofitModule
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.factory().create(applicationContext, retrofitModule)
    }
}