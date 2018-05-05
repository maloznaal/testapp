package com.example.nursultan.testapp.data.db

import com.example.nursultan.testapp.data.db.model.DaoMaster
import com.example.nursultan.testapp.data.db.model.DaoSession
import com.example.nursultan.testapp.data.db.model.Query
import com.example.nursultan.testapp.di.TestApplicationScope
import com.example.nursultan.testapp.dummy.DummyContent
import io.reactivex.Observable
import javax.inject.Inject


@TestApplicationScope
class AppDbHelper @Inject
constructor(dbOpenHelper: DbOpenHelper) : DbHelper {

    private var mDaoSession: DaoSession


    override fun extractQueries(): Observable<List<Query>> {
        return Observable.fromCallable { mDaoSession.queryDao.loadAll() }
    }

    override fun extractPhotos(): Observable<List<DummyContent.DummyItem>> {
        return Observable.fromCallable { DummyContent.ITEMS  }
    }

    init {
        mDaoSession = DaoMaster(dbOpenHelper.writableDb).newSession()
    }

    override fun insertQuery(query: Query): Observable<Long> {
        return Observable.fromCallable { mDaoSession.queryDao.insert(query) }
    }
}

