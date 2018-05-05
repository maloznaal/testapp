package com.example.nursultan.testapp.data.db

import android.content.Context
import com.example.nursultan.testapp.data.db.model.DaoMaster
import com.example.nursultan.testapp.di.ApplicationContext
import com.example.nursultan.testapp.di.DatabaseInfo
import com.example.nursultan.testapp.di.TestApplicationScope
import org.greenrobot.greendao.database.Database
import timber.log.Timber
import javax.inject.Inject


@TestApplicationScope
class DbOpenHelper @Inject
constructor(@ApplicationContext context: Context, @DatabaseInfo name: String) : DaoMaster.OpenHelper(context, name) {

    // handle db migrations ? optional.
    override fun onUpgrade(db: Database?, oldVersion: Int, newVersion: Int) {
        super.onUpgrade(db, oldVersion, newVersion)
        when (oldVersion) {
            1 -> Timber.d("migration applied")
        }
    }
}

