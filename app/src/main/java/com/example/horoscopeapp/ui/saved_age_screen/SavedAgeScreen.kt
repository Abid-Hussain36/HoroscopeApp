package com.example.horoscopeapp.ui.saved_age_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.horoscopeapp.data.database.SavedAge


@Composable
fun SavedAgeScreen(
    viewModel: SavedAgeScreenViewModel = hiltViewModel()
){
    val savedAgeData by viewModel.savedAgeData.observeAsState(emptyList())
    SavedAgeList(
        savedAgeData = savedAgeData,
        deleteSavedAge = {deletedData -> viewModel.deleteSavedAge(deletedData)},
        modifier = Modifier.padding(28.dp)
    )
}

@Composable
fun SavedAgeList(
    savedAgeData: List<SavedAge>?,
    deleteSavedAge: (SavedAge) -> Unit,
    modifier: Modifier
){
    LazyColumn(modifier = modifier){
        items(savedAgeData ?: emptyList()){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ){
                    Text(
                        text = it.name.toString(),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Number of People Named ${it.name.toString()}: ${it.count.toString()}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                    Text(
                        text = "Average Age of People Named ${it.name.toString()}: ${it.age.toString()}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Box(
                       contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    ){
                        Button(
                            onClick = {
                                deleteSavedAge(it)
                            }
                        ) {
                            Text(text = "Delete")
                        }
                    }
                }
            }
        }
    }
}