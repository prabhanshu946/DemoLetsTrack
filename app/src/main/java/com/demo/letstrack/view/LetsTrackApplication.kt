package com.demo.letstrack.view

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class LetsTrackApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    companion object{
        lateinit var instance: LetsTrackApplication
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
    override fun getWorkManagerConfiguration() = Configuration.Builder().setWorkerFactory(workerFactory).build()
}