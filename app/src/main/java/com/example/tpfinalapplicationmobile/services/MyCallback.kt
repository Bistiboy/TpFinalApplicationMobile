package com.example.tpfinalapplicationmobile.services

import com.example.tpfinalapplicationmobile.models.MyList

interface MyCallback {
    fun onDataReceived(items: List<MyList>)

    fun onError(error: String)
}