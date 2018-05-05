package com.example.nursultan.testapp.di.component

import android.content.Context

import com.example.nursultan.testapp.TestApp
import com.example.nursultan.testapp.data.DataManager
import com.example.nursultan.testapp.di.ApplicationContext
import com.example.nursultan.testapp.di.TestApplicationScope
import com.example.nursultan.testapp.di.module.ApiServiceModule
import com.example.nursultan.testapp.di.module.ContextModule
import com.example.nursultan.testapp.di.module.PicassoModule
import com.example.nursultan.testapp.network.ApiService
import com.squareup.picasso.Picasso

import dagger.Component

@TestApplicationScope
@Component(modules = arrayOf(ApiServiceModule::class, PicassoModule::class, ContextModule::class))
interface TestAppComponent {
    fun inject(app: TestApp)

    @ApplicationContext
    fun context(): Context

    fun dataManager(): DataManager
    fun api(): ApiService
    fun picasso(): Picasso
}
