package com.rapidzz.presero.Utils.Application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate


class GarageApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.MODE_NIGHT_YES
    }


}