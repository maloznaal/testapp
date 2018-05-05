package com.example.nursultan.testapp.di.component

import com.example.nursultan.testapp.di.PerActivity
import com.example.nursultan.testapp.di.module.ActivityModule
import com.example.nursultan.testapp.screens.HomeActivity
import com.example.nursultan.testapp.ui.detail.DetailFragment
import com.example.nursultan.testapp.ui.photo.PhotoFragment

import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(TestAppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(fragment: PhotoFragment)
    fun inject(fragment: DetailFragment)
    fun inject(activity: HomeActivity)
}
