package com.example.topcrypto

import android.app.Application
import android.content.Context
import com.example.topcrypto.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CryptoApplication: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        Timber.d("initTimber")
        if (BuildConfig.DEBUG || BuildConfig.BUILD_TYPE == "pre_release" || BuildConfig.BUILD_TYPE == "release") {
            Timber.plant(Timber.DebugTree())
        }
    }
}