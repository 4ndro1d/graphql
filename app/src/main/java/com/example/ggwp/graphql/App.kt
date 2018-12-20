package com.example.ggwp.graphql

import android.app.Application
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))

        Timber.plant(Timber.DebugTree())
    }
}