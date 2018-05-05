package com.example.nursultan.testapp.ui.photo

import com.example.nursultan.testapp.dummy.DummyContent
import com.example.nursultan.testapp.ui.base.MvpView

interface PhotoMvpView : MvpView {
    fun updateData(photoList: List<DummyContent.DummyItem>)
    fun updateSuggestions(list: List<String>)
    fun showFull(path: String)
}