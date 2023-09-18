package com.example.horoscopeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedAge(
    val count: Int,
    @PrimaryKey val name: String,
    val age: Int
)
