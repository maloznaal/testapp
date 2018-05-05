package com.example.nursultan.testapp.screens

import android.os.Bundle
import butterknife.ButterKnife
import com.example.nursultan.testapp.R
import com.example.nursultan.testapp.ui.base.BaseActivity
import com.example.nursultan.testapp.ui.base.MvpView
import com.example.nursultan.testapp.ui.photo.PhotoFragment


class HomeActivity : BaseActivity(), MvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        activityComponent!!.inject(this)
        setUnBinder(ButterKnife.bind(this))

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.frame_container, PhotoFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
        }
    }

    override fun setUp() {
        // set toolbar title and etc.
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            return;
        }
    }

    companion object {
        private val TAG = "HomeActivity"
        private val COL_COUNT = 3
    }
}
