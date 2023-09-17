package com.example.horoscopeapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Age(
    val count: Int? = null,
    val name: String? = null,
    val age: Int? = null
)