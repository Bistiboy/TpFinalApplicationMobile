package com.example.tpfinalapplicationmobile.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tpfinalapplicationmobile.R
import com.example.tpfinalapplicationmobile.models.MyList
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.element_shopping_list.*

class ElementShoppingListActivity : AppCompatActivity(){

    private lateinit var postReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.element_shopping_list)

        // Initialize DB
        val idListe = "3"

        postReference = FirebaseDatabase.getInstance().reference.child(idListe)

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val liste = dataSnapshot.getValue(MyList::class.java)

                listName.setText(liste?.listName)
                listDate.setText(liste?.date)
                listContent.setText(liste?.content)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Log.w(Tag, "loadList:onCancelled", databaseError.toException())
            }
        }
        postReference.addValueEventListener(postListener)


        // Bouton permetant de finir l'activité en cours
        btn_cancel_show_content.setOnClickListener {
            this.finish()
        }
    }
}