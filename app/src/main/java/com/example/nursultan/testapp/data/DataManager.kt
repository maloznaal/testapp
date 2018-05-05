package com.example.nursultan.testapp.data

import com.example.nursultan.testapp.data.db.DbHelper

interface DataManager : DbHelper {
    fun updateApiHeader()
}
