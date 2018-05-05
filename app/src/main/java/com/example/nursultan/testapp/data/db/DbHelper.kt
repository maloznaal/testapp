package com.example.nursultan.testapp.data.db

import com.example.nursultan.testapp.data.db.model.Query
import com.example.nursultan.testapp.dummy.DummyContent

import io.reactivex.Observable

interface DbHelper {

    fun extractQueries(): Observable<List<Query>>

    // this method should never belong here, for testing
    fun extractPhotos(): Observable<List<DummyContent.DummyItem>>

    fun insertQuery(query: Query): Observable<Long>
}

