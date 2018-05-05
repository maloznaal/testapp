package com.example.nursultan.testapp.ui.detail

import com.example.nursultan.testapp.ui.base.MvpPresenter

interface DetailMvpPresenter<V : DetailMvpView> : MvpPresenter<V> {
    fun onViewPrepared()
}
