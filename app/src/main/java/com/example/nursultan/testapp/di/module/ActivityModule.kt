package com.example.nursultan.testapp.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.ArrayAdapter

import com.example.nursultan.testapp.di.ActivityContext
import com.example.nursultan.testapp.ui.detail.DetailMvpPresenter
import com.example.nursultan.testapp.ui.detail.DetailMvpView
import com.example.nursultan.testapp.ui.detail.DetailPresenter
import com.example.nursultan.testapp.ui.photo.PhotoMvpPresenter
import com.example.nursultan.testapp.ui.photo.PhotoMvpView
import com.example.nursultan.testapp.ui.photo.PhotoPresenter
import com.example.nursultan.testapp.utils.AppConstants
import com.example.nursultan.testapp.utils.rx.AppSchedulerProvider
import com.example.nursultan.testapp.utils.rx.SchedulerProvider

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun context(): Context {
        return activity
    }

    @Provides
    fun activity(): AppCompatActivity {
        return activity
    }


    @Provides
    fun provideLinearLayoutManager(activity: AppCompatActivity): GridLayoutManager {
        return GridLayoutManager(activity, AppConstants.SPAN_COUNT) // numerical value in app.const
    }

    @Provides
    fun provideArrayAdapter(activity: AppCompatActivity): ArrayAdapter<String> {
        return ArrayAdapter(activity, android.R.layout.simple_list_item_1)
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    fun providePhotoMvpPresenter(
            presenter: PhotoPresenter<PhotoMvpView>): PhotoMvpPresenter<PhotoMvpView> {
        return presenter
    }

    @Provides
    fun provideDetailMvpPresenter(
            presenter: DetailPresenter<DetailMvpView>): DetailMvpPresenter<DetailMvpView> {
        return presenter
    }

}
