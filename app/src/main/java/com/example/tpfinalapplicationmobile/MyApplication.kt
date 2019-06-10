package com.example.tpfinalapplicationmobile

import android.app.Application
import com.example.tpfinalapplicationmobile.services.ShoppingListService

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ShoppingListService.initService(this)
    }
}