package com.route.newsappc34

import android.app.Application
import com.route.newsappc34.dataBase.NewsDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application () {
    override fun onCreate() {
        super.onCreate()
        NewsDataBase.init(this);
        NetworkAwareHandlerImpl.init(this)
    }
}