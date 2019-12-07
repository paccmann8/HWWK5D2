package com.example.hwwk5d2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hwwk5d2.R
import com.example.hwwk5d2.model.Guest
import kotlinx.android.synthetic.main.fragment_display_guests.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe



class DisplayGuests : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_guests, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EventBus.getDefault().register(this)

    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun receiveCust(cust: Guest){

        names.text = "${cust.name}"
    }



}