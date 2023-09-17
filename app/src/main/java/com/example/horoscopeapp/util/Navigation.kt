package com.example.horoscopeapp.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.horoscopeapp.ui.home_screen.HomeScreen
import com.example.horoscopeapp.ui.saved_age_screen.SavedAgeScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController)}
    ) {
        Box(modifier = Modifier.padding(it)){
            NavHost(navController = navController, startDestination = Home.route){
                composable(Home.route){
                    HomeScreen()
                }
                composable(Saved.route){
                    SavedAgeScreen()
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController){
    val destinationList = listOf(
        Home,
        Saved
    )
    var selectedIndex by rememberSaveable{
        mutableStateOf(0)
    }
    BottomNavigation(
        backgroundColor = Color(0xFF00D100)
    ) {
        destinationList.forEachIndexed{index, destination ->
            BottomNavigationItem(
                label = {
                    Text(
                        text = destination.title,
                        modifier = Modifier.padding(top = 4.dp),
                        color = Color.White
                    )
                },
                icon = {
                       Icon(
                           painter = painterResource(id = destination.icon),
                           contentDescription = destination.title,
                           tint = Color.White
                       )
                },
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    navController.navigate(destination.route){
                        popUpTo(Home.route)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}