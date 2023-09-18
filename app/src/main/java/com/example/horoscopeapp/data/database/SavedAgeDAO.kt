package com.example.horoscopeapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SavedAgeDAO {
    @Insert
    suspend fun insertSavedAge(savedAge: SavedAge)

    @Delete
    suspend fun deleteSavedAge(savedAge: SavedAge)

    @Query("SELECT * from SavedAge")
    suspend fun getAllAges(): List<SavedAge>
}