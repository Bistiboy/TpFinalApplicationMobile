package com.example.tpfinalapplicationmobile.services

import android.content.Context
import com.example.tpfinalapplicationmobile.models.MyList
import com.google.firebase.database.*

private const val REF_SHOPPING_LISTS = "shoppingLists"

object ShoppingListService {
    lateinit var database: FirebaseDatabase
    lateinit var listsRef: DatabaseReference

    lateinit var listener: ValueEventListener

    private var callback: MyCallback? = null


    fun initService(context: Context?) {
        database = FirebaseDatabase.getInstance()
        listsRef = database.getReference(REF_SHOPPING_LISTS)
        listener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                callback?.onError(error.message)
            }

            override fun onDataChange(data: DataSnapshot) {
                val itemsList = ArrayList<MyList>()
                data.children.forEach { child ->
                    child.getValue(MyList::class.java)?.let { myList ->
                        itemsList.add(myList)
                    }
                }
                callback?.onDataReceived(itemsList)
            }
        }
    }

    fun shoppingListListener(callback: MyCallback) {
        this.callback = callback
        listsRef.addValueEventListener(listener)
    }

    fun addItem(item: MyList) {
        val key = listsRef.push().key ?: ""
        item.key = key
        listsRef.child(key).setValue(item)
    }

    fun updateItem(item: MyList) {
        listsRef.child(item.key).setValue(item)
    }

    fun removeListener() {
        this.callback = null
        listsRef.removeEventListener(listener)
    }
}

