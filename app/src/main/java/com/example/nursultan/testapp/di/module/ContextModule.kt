package com.example.nursultan.testapp.di.module

//import com.example.nursultan.testapp.data.db.AppDbHelper

import android.content.Context
import com.example.nursultan.testapp.data.AppDataManager
import com.example.nursultan.testapp.data.DataManager
import com.example.nursultan.testapp.data.db.AppDbHelper
import com.example.nursultan.testapp.data.db.DbHelper
import com.example.nursultan.testapp.di.ApplicationContext
import com.example.nursultan.testapp.di.DatabaseInfo
import com.example.nursultan.testapp.di.TestApplicationScope
import com.example.nursultan.testapp.utils.AppConstants
import dagger.Module
import dagger.Provides

@Module
class ContextModule(context: Context) {

    private val context: Context

    init {
        this.context = context.applicationContext
    }

    @Provides
    @TestApplicationScope
    @ApplicationContext
    fun context(): Context {
        return this.context
    }


    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return AppConstants.DB_NAME
    }

    @Provides
    @TestApplicationScope
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @TestApplicationScope
    fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }

}
