package com.example.nursultan.testapp.data

import android.content.Context
import com.example.nursultan.testapp.data.db.DbHelper
import com.example.nursultan.testapp.data.db.model.Query
import com.example.nursultan.testapp.di.ApplicationContext
import com.example.nursultan.testapp.di.TestApplicationScope
import com.example.nursultan.testapp.dummy.DummyContent
import com.example.nursultan.testapp.network.ApiService
import io.reactivex.Observable
import javax.inject.Inject

@TestApplicationScope
class AppDataManager @Inject
constructor(@param:ApplicationContext private val mContext: Context,
            private val mDbHelper: DbHelper, private val api: ApiService) : DataManager {

    override fun updateApiHeader() {
        // update header parameters in network client
    }


    override fun insertQuery(query: Query): Observable<Long> {
        return mDbHelper.insertQuery(query)
    }

    override fun extractQueries(): Observable<List<Query>> {
        return mDbHelper.extractQueries()
    }

    override fun extractPhotos(): Observable<List<DummyContent.DummyItem>> {
        return mDbHelper.extractPhotos()
    }

    companion object {

        private val TAG = "AppDataManager"
    }

}
