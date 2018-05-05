package com.example.nursultan.testapp

import android.app.Activity
import android.app.Application
import com.example.nursultan.testapp.data.DataManager
import com.example.nursultan.testapp.di.component.DaggerTestAppComponent
import com.example.nursultan.testapp.di.component.TestAppComponent
import com.example.nursultan.testapp.di.module.ContextModule
import timber.log.Timber
import javax.inject.Inject

class TestApp : Application() {

    @Inject
    lateinit var mDataManager: DataManager

    lateinit var component: TestAppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        component = DaggerTestAppComponent.builder()
                .contextModule(ContextModule(this))
                .build()

        component.inject(this)

    }

    companion object {

        operator fun get(activity: Activity): TestApp {
            return activity.application as TestApp
        }
    }
}
