package com.example.horoscopeapp.ui.saved_age_screen

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.horoscopeapp.data.database.SavedAge


@Composable
fun SavedAgeScreen(
    viewModel: SavedAgeScreenViewModel = hiltViewModel()
){
    val savedAgeData by viewModel.savedAgeData.observeAsState(emptyList())
    SavedAgeList(
        savedAgeData = savedAgeData,
        deleteSavedAge = {deletedData -> viewModel.deleteSavedAge(deletedData)},
        updateSavedAgeData = {viewModel.updateSavedAgeData()},
        modifier = Modifier.padding(28.dp)
    )
}

@Composable
fun SavedAgeList(
    savedAgeData: List<SavedAge>?,
    deleteSavedAge: (SavedAge) -> Unit,
    updateSavedAgeData: () -> Unit,
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
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                        )
                ){
                    Text(text = it.name.toString())
                    Text(text = it.count.toString())
                    Text(text = it.age.toString())
                    Button(
                        onClick = {
                            deleteSavedAge(it)
                            updateSavedAgeData()
                        }
                    ) {
                        Text(text = "Delete")
                    }
                }
            }
        }
    }
}