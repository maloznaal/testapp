package com.example.nursultan.testapp.ui.photo

import com.example.nursultan.testapp.data.DataManager
import com.example.nursultan.testapp.data.db.model.Query
import com.example.nursultan.testapp.dummy.DummyContent
import com.example.nursultan.testapp.network.ApiService
import com.example.nursultan.testapp.ui.base.BasePresenter
import com.example.nursultan.testapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import java.util.*
import javax.inject.Inject

class PhotoPresenter<V : PhotoMvpView> @Inject
constructor(dataManager: DataManager,
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            apiService: ApiService) : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable, apiService), PhotoMvpPresenter<V> {


    override fun onViewPrepared() {
        getQueries()
        mvpView!!.updateData(DummyContent.ITEMS)
    }

    override fun requestSearchUpdate(query: String) {
        if (query.trim { it <= ' ' } == "") {
            return
        }
        mvpView!!.showLoading()
        compositeDisposable.add(dataManager
                .extractPhotos()
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .subscribe(Consumer { list ->
                    mvpView!!.updateData(filterList(query, list))
                    mvpView!!.hideLoading()
                }, Consumer {
                    if (!isViewAttached) {
                        return@Consumer
                    }
                    mvpView!!.hideLoading()
                    // handle the error here
                }))
    }


    override fun insertSearchQuery(query: String) {
        if (query.trim { it <= ' ' } == "") {
            return
        }
        var inst = Query()
        inst.queryText = query
        mvpView!!.showLoading()
        compositeDisposable.add(dataManager
                .insertQuery(inst)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(Consumer<Any> { long ->
                    getQueries()
                }, Consumer {

                    if (!isViewAttached) {
                        return@Consumer
                    }
                    // handle the error here
                }))
    }

    override fun getQueries() {
        compositeDisposable.add(dataManager
                .extractQueries()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(Consumer { queries ->
                    val list = removeDuplicates(queries)
                    if (list.isNotEmpty()) {
                        mvpView!!.updateSuggestions(list)
                    }
                }, Consumer {
                    if (!isViewAttached) {
                        return@Consumer
                    }
                    // handle the error here
                }))
    }

    override fun goDetail(path: String) {
        mvpView!!.showFull(path)
    }

    private fun removeDuplicates(queries: List<Query>): List<String> {
        val list = ArrayList<String>()
        for (i in queries.indices) {
            if (queries[i].queryText != null) {
                list.add(queries[i].queryText!!)
            }
        }
        val hs = HashSet<String>()
        hs.addAll(list)
        list.clear()
        list.addAll(hs)
        return list
    }

    private fun filterList(queryText: String, list: List<DummyContent.DummyItem>): List<DummyContent.DummyItem> {
        val filteredList = ArrayList<DummyContent.DummyItem>()
        for (i in list.indices) {
            val cur = list[i]
            if (cur.title.toLowerCase().contains(queryText)) {
                filteredList.add(cur)
            }
        }
        return filteredList
    }

}
