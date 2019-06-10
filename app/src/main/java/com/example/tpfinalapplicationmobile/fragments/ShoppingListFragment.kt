package com.example.tpfinalapplicationmobile.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tpfinalapplicationmobile.activities.AddShoppingListActivity
import com.example.tpfinalapplicationmobile.R.layout.shopping_list_main
import com.example.tpfinalapplicationmobile.activities.ElementShoppingListActivity
import com.example.tpfinalapplicationmobile.adapter.ShoppingListAdapter
import com.example.tpfinalapplicationmobile.models.MyList
import com.example.tpfinalapplicationmobile.services.MyCallback
import com.example.tpfinalapplicationmobile.services.OnItemClickListener
import com.example.tpfinalapplicationmobile.services.ShoppingListService
import com.example.tpfinalapplicationmobile.services.addOnItemClickListener
import kotlinx.android.synthetic.main.shopping_list_main.*
import kotlinx.android.synthetic.main.shopping_list_main.view.*

class ShoppingListFragment : Fragment(), MyCallback {

    private lateinit var adapter: ShoppingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(shopping_list_main, container, false)

        ShoppingListService.initService(this.context)

        adapter = ShoppingListAdapter()
        var rViewShoppingList = view.recyclerViewShoppingList

        rViewShoppingList.layoutManager = LinearLayoutManager(this.context)
        rViewShoppingList.adapter = adapter


        // Permet de lancer l'activité qui affiche les informations d'une liste de course en cliquant sur le nom d'une liste
        /*rViewShoppingList.addOnItemClickListener(object: OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {
                val intent = Intent(context, ElementShoppingListActivity::class.java)
                startActivity(intent)
            }
        })*/

        // Bouton permetant de lancer l'activité qui permet d'ajouter une liste de course dans la base de données
        view.fabShoppingList.setOnClickListener{
            val intent = Intent(context, AddShoppingListActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        ShoppingListService.shoppingListListener(this)
    }

    override fun onDataReceived(items: List<MyList>) {
        adapter.fillView(items)
    }

    override fun onError(error: String) {
        Log.d("OnERROR", error)
    }
}