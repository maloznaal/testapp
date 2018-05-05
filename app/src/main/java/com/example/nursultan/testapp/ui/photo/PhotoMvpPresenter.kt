package com.example.nursultan.testapp.ui.photo

import com.example.nursultan.testapp.ui.base.MvpPresenter

interface PhotoMvpPresenter<V : PhotoMvpView> : MvpPresenter<V> {

    fun onViewPrepared()

    fun insertSearchQuery(query: String)

    fun getQueries()

    fun requestSearchUpdate(query: String)

    fun goDetail(path: String)
}
