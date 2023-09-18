package com.example.horoscopeapp.ui.saved_age_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.horoscopeapp.data.database.SavedAge


@Composable
fun SavedAgeScreen(
    viewModel: SavedAgeScreenViewModel = hiltViewModel()
){
    val savedAgeData by viewModel.savedAgeData.observeAsState(emptyList())
    if(savedAgeData.isEmpty()){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(Color(0xFFE1D9D1), Color(0xFF00D100))))
                .padding(top = 28.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "You have no saved data",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
    else{
        SavedAgeList(
            savedAgeData = savedAgeData,
            deleteSavedAge = { deletedData -> viewModel.deleteSavedAge(deletedData) },
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(Color(0xFFE1D9D1), Color(0xFF00D100))))
                .padding(28.dp)
        )
    }
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
                    .padding(bottom = 28.dp),
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
                        text = it.name,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Average Age of People Named ${it.name}: ${it.age}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = "Number of People Named ${it.name}: ${it.count}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    Box(
                       contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ){
                        Button(
                            onClick = {
                                deleteSavedAge(it)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF00D100),
                                contentColor = Color(0xFFFFFFFF)
                            )
                        ) {
                            Text(
                                text = "Delete",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}