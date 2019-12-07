package com.example.hwwk5d2.database

import android.database.Cursor
import androidx.room.*

@Dao
interface HotelDAO {

    @Insert
    fun addNewRoom(HotelEntity: HotelEntity)

    @Query("SELECT * FROM Hotel")

    fun retrieveAllRooms(): List<HotelEntity>


    @Query("SELECT * FROM Hotel")
    fun retrieveAllGuests() : Cursor




}