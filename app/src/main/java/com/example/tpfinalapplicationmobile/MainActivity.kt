package com.example.tpfinalapplicationmobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tpfinalapplicationmobile.adapter.ShoppingListAdapter
import com.example.tpfinalapplicationmobile.fragments.AgendaFragment
import com.example.tpfinalapplicationmobile.fragments.ExpenseFragment
import com.example.tpfinalapplicationmobile.fragments.HomeFragment
import com.example.tpfinalapplicationmobile.fragments.ShoppingListFragment
import com.example.tpfinalapplicationmobile.models.MyList
import com.example.tpfinalapplicationmobile.services.MyCallback
import com.example.tpfinalapplicationmobile.services.ShoppingListService
import com.example.tpfinalapplicationmobile.utils.LocaleHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.shopping_list_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mLanguageCode = "en"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        val homeFragment = HomeFragment()
        val manager = supportFragmentManager

        val transaction = manager.beginTransaction()

        transaction.replace(R.id.fragment_container, homeFragment)
        transaction.addToBackStack(null)

        transaction.commit()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    // Menu
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val manager = supportFragmentManager
        val utils = LocaleHelper()


        when (item.itemId) {
            R.id.nav_home -> {
                val homeFragment = HomeFragment()

                val transaction = manager.beginTransaction()

                transaction.replace(R.id.fragment_container, homeFragment)
                transaction.addToBackStack(null)

                transaction.commit()
            }
            R.id.nav_shoppingList -> {
                val shoppingListFragment = ShoppingListFragment()

                val transaction = manager.beginTransaction()

                transaction.replace(R.id.fragment_container, shoppingListFragment)
                transaction.addToBackStack(null)

                transaction.commit()

                // var liste = MyList("Nico", "2")
                // database.child("2").setValue(liste)

            }
            R.id.nav_expense -> {
                val expenseFragment = ExpenseFragment()

                val transaction = manager.beginTransaction()

                transaction.replace(R.id.fragment_container, expenseFragment)
                transaction.addToBackStack(null)

                transaction.commit()
            }
            R.id.nav_agenda -> {
                val agendaFragment = AgendaFragment()

                val transaction = manager.beginTransaction()

                transaction.replace(R.id.fragment_container, agendaFragment)
                transaction.addToBackStack(null)

                transaction.commit()
            }
            R.id.nav_fr -> {

                mLanguageCode = "fr"
                utils.setLocale(this, mLanguageCode)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)


            }
            R.id.nav_en -> {

                mLanguageCode = "en"
                utils.setLocale(this, mLanguageCode)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
