package com.example.nursultan.testapp.di.module

import com.example.nursultan.testapp.di.TestApplicationScope
import com.example.nursultan.testapp.network.ApiService
import com.example.nursultan.testapp.network.DateTimeConverter
import com.example.nursultan.testapp.utils.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = arrayOf(NetworkModule::class))
class ApiServiceModule {

    @Provides
    @TestApplicationScope
    fun apiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @TestApplicationScope
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(DateTime::class.java, DateTimeConverter())
        return gsonBuilder.create()
    }

    @Provides
    @TestApplicationScope
    fun retrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(AppConstants.BASE_URL)
                .build()
    }
}
