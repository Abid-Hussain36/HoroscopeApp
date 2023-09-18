package com.example.horoscopeapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SavedAge::class], version = 1)
abstract class SavedAgeDatabase: RoomDatabase() {
    abstract fun savedAgeDAO(): SavedAgeDAO
}