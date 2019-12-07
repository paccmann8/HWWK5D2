package com.example.hwwk5d2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.hwwk5d2.R
import com.example.hwwk5d2.database.HotelDatabase
import com.example.hwwk5d2.database.HotelEntity
import kotlinx.android.synthetic.main.guest_insert_layout.*

class InputFragment : Fragment() {

    interface FragmentListener{
        fun updateResults()
    }

    fun setListener(fragmentListener: FragmentListener){
        this.fragmentListener = fragmentListener
    }


    lateinit var fragmentListener: FragmentListener
    lateinit var database: HotelDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.guest_insert_layout, container, false)
        return view



    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        activity?.let { fragmentActivity ->
            database = Room.databaseBuilder(
                fragmentActivity.applicationContext,
                HotelDatabase::class.java,
                "Hotel.db"
            )
                .allowMainThreadQueries()
                .build()
        }


        add_guest_button.setOnClickListener {
            val room = roomNumber_edittext.text.toString()
            val name = guestName_edittext.text.toString()
            val hotelEntity = HotelEntity(room, name)
            database.HotelDAO().addNewRoom(hotelEntity)
            clearTextFields()
            fragmentListener.updateResults()
            fragmentManager?.popBackStack()
        }
    }


    private fun clearTextFields() {
        guestName_edittext.text.clear()
        roomNumber_edittext.text.clear()
    }
}