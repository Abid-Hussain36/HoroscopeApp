package com.example.horoscopeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedAge(
    val count: Int? = null,
    val name: String? = null,
    val age: Int? = null,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
    )
