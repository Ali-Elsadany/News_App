package com.pi.newsc40.ui

import android.app.Application
import com.pi.newsc40.data.database.MyDataBase
import com.pi.newsc40.data.utils.InternetConnectionChecker
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        //todo: Remove this line with something better
        InternetConnectionChecker.context = this
        MyDataBase.init(this)
    }
}