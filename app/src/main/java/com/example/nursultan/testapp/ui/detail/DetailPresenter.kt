package com.example.nursultan.testapp.ui.detail

import com.example.nursultan.testapp.data.DataManager
import com.example.nursultan.testapp.network.ApiService
import com.example.nursultan.testapp.ui.base.BasePresenter
import com.example.nursultan.testapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailPresenter<V : DetailMvpView> @Inject
constructor(dataManager: DataManager,
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            apiService: ApiService) : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable, apiService), DetailMvpPresenter<V> {


    override fun onViewPrepared() {
        mvpView!!.loadImage()
    }
}
