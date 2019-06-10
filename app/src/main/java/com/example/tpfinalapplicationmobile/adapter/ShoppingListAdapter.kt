package com.example.tpfinalapplicationmobile.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tpfinalapplicationmobile.R
import com.example.tpfinalapplicationmobile.activities.ElementShoppingListActivity
import com.example.tpfinalapplicationmobile.models.MyList
import kotlinx.android.synthetic.main.item_shopping_list.view.*
import kotlinx.android.synthetic.main.shopping_list_main.view.*

class ShoppingListAdapter : RecyclerView.Adapter<ShoppingListAdapter.MyViewHolder>() {

    private val listItems: ArrayList<MyList> = ArrayList()

    fun fillView(data : List<MyList>) {
        listItems.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shopping_list, parent, false))
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(listItems[position])
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView

        init {
            name = itemView.tvItemShoppingList
            itemView.setOnClickListener{

            }
        }

        fun bind(item: MyList) {
            name.text = item.listName
        }
    }
}