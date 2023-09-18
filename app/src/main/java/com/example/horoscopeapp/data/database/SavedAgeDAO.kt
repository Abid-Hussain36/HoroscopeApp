package com.example.horoscopeapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SavedAgeDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSavedAge(savedAge: SavedAge)

    @Delete
    suspend fun deleteSavedAge(savedAge: SavedAge)

    @Query("SELECT * from SavedAge")
    suspend fun getAllAges(): List<SavedAge>
}