package com.example.nursultan.testapp.ui.base

import com.example.nursultan.testapp.data.DataManager
import com.example.nursultan.testapp.network.ApiService
import com.example.nursultan.testapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BasePresenter<V : MvpView> @Inject
constructor(val dataManager: DataManager,
            val schedulerProvider: SchedulerProvider,
            val compositeDisposable: CompositeDisposable,
            val api: ApiService) : MvpPresenter<V> {

    var mvpView: V? = null
        private set

    val isViewAttached: Boolean
        get() = mvpView != null

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        mvpView = null
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }


    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.onAttach(MvpView) before" + " requesting data to the Presenter")

    companion object {

        private val TAG = "BasePresenter"
    }
}
