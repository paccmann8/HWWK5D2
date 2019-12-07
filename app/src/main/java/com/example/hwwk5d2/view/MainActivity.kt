package com.example.hwwk5d2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.hwwk5d2.R
import com.example.hwwk5d2.adapters.GuestAdapter
import com.example.hwwk5d2.database.HotelDatabase
import com.example.hwwk5d2.view.DisplayGuests
import com.example.hwwk5d2.view.DisplayRooms
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), InputFragment.FragmentListener {

    lateinit var HotelDB: HotelDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        HotelDB = Room.databaseBuilder(
            this,
            HotelDatabase::class.java,
            "Hotel.db"
        )
            .allowMainThreadQueries()
            .build()






        list_button.setOnClickListener {
            openfrags()


            setUpAdapter()

        }

        val itemDecoration =  DividerItemDecoration(this, VERTICAL)
        guest_recyclerview.addItemDecoration(itemDecoration)
        guest_recyclerview.addItemDecoration(itemDecoration)
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is InputFragment)
            (fragment).setListener(this)
    }

    private fun setUpAdapter() {
        val adapter = GuestAdapter(HotelDB.HotelDAO().retrieveAllRooms())
        guest_recyclerview.adapter = adapter
        guest_recyclerview.layoutManager = LinearLayoutManager(this)
    }

    fun openfrags(){

        val fragment = DisplayRooms()
        supportFragmentManager.beginTransaction()
            .replace(R.id.room_layout, fragment)
            .addToBackStack(fragment.tag)
            .commit()

        val fragment2 = DisplayGuests()
        supportFragmentManager.beginTransaction()
            .replace(R.id.customer_layout, fragment2)
            .addToBackStack(fragment2.tag)
            .commit()

    }




    override fun updateResults() {
        setUpAdapter()
    }
}