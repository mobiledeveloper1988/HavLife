package com.hav.havlife

import android.annotation.SuppressLint
import android.app.Application
import com.hav.havlife.data.data_util.AppPreference

class HavLifeApp : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        var appPreference: AppPreference? = null
    }
    override fun onCreate() {
        super.onCreate()
        appPreference = AppPreference(this@HavLifeApp)

    }
}