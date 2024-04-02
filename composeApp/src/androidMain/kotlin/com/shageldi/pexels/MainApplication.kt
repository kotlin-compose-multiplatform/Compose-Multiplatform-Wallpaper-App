package com.shageldi.pexels

import android.app.Application
import android.content.Context
import features.images.di.imagesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(imagesModule)
        }
    }
}