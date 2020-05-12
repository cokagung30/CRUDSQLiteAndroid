package com.baliwork.trainingcrudsqlite

import android.app.Application
import com.baliwork.trainingcrudsqlite.sqlite.AppDatabase
import com.facebook.stetho.Stetho

class Application : Application()  {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.createDatabase(this)
        Stetho.initializeWithDefaults(this)
    }
}