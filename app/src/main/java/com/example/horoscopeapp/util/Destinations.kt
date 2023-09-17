package com.example.horoscopeapp.util

import com.example.horoscopeapp.R

interface Destinations {
    val route: String
    val icon: Int
    val title: String
}

object Home: Destinations{
    override val route = "Home"
    override val icon = R.drawable.baseline_elderly_24
    override val title = "Home"
}

object Saved: Destinations{
    override val route = "Saved"
    override val icon = R.drawable.baseline_bookmark_24
    override val title = "Saved"
}