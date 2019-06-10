package com.example.tpfinalapplicationmobile.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class MyList (

    //Model class
    var listName: String = "",
    var key: String = "",
    var date: String = "",
    var content: String = ""
)