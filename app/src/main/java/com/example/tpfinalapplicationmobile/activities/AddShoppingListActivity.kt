package com.example.tpfinalapplicationmobile.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tpfinalapplicationmobile.R
import com.example.tpfinalapplicationmobile.models.MyList
import com.example.tpfinalapplicationmobile.services.ShoppingListService
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.add_shopping_list.*

class AddShoppingListActivity : AppCompatActivity(){

    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_shopping_list)

        // Initialisation de la BDD
        database = FirebaseDatabase.getInstance().reference


        // Bouton permetant de finir l'activité en cours
        btn_cancel.setOnClickListener {
            this.finish()
        }

        // Bouton pour envoyer les donnée saisie dans la base de donnée
        btn_confirm.setOnClickListener {

            val newList = MyList()
            val idList = "3"

            newList.listName = et_name_list.text.toString()
            newList.content = et_content.text.toString()
            newList.date = et_date.text.toString()
            newList.key = "1"

            ShoppingListService.addItem(newList)
            this.finish()
        }
    }
}