package com.example.nursultan.testapp.di.module

import android.content.Context
import com.example.nursultan.testapp.di.ApplicationContext
import com.example.nursultan.testapp.di.TestApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File

@Module(includes = arrayOf(ContextModule::class))
class NetworkModule {

    @Provides
    @TestApplicationScope
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.i(message) })
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Provides
    @TestApplicationScope
    fun cacheFile(@ApplicationContext context: Context): File {
        return File(context.cacheDir, "okhttp_cache")
    }

    @Provides
    @TestApplicationScope
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, (10 * 1024 * 1024).toLong())
    }

    @Provides
    @TestApplicationScope
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build()
    }

}
