package com.example.nursultan.testapp.di.module

import android.content.Context

import com.example.nursultan.testapp.di.ApplicationContext
import com.example.nursultan.testapp.di.TestApplicationScope
import com.squareup.picasso.Picasso

import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(ContextModule::class, NetworkModule::class))
class PicassoModule {

    @Provides
    @TestApplicationScope
    fun picasso(@ApplicationContext context: Context): Picasso {
        return Picasso.Builder(context)
                .build()
    }

}
