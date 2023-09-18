package com.example.horoscopeapp.ui.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.horoscopeapp.R
import com.example.horoscopeapp.data.network.dto.Age


@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    val ageData by viewModel.ageData.observeAsState(initial = Age())
    val searchText by viewModel.searchText.observeAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xFFE1D9D1), Color(0xFF00D100))))
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AgeSearchField(
            searchText = searchText,
            onChangeSearchText = { text -> viewModel.onSearchTextChanged(text) },
            getAge = {searchName -> viewModel.getAge(searchName)},
            clearText = {viewModel.clearSearchText()},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        if(!ageData.name.isNullOrEmpty()){
            val formattedName = ageData.name!![0].toUpperCase().toString() + ageData.name!!.drop(1)
            Text(
                text = "You typed in ${formattedName}",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 10.dp, end = 10.dp),
                style = TextStyle(lineHeight = 32.sp),
                color = Color.White
            )
        }
        else{
            Text(
                text = "Type in a name to learn some cool stuff!",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 10.dp, end = 10.dp),
                style = TextStyle(lineHeight = 32.sp),
                color = Color.White
            )
        }
        if(ageData.age != null){
            Text(
                text = "The average age for someone with this name is ${ageData.age.toString()} years old!",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 10.dp, end = 10.dp),
                style = TextStyle(lineHeight = 32.sp),
                color = Color.White
            )
        }
        else{
            Text(
                text = "Type in a name to see what its average age will be",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 10.dp, end = 10.dp),
                style = TextStyle(lineHeight = 32.sp),
                color = Color.White
            )
        }
        if(ageData.count != null){
            val formattedName = ageData.name!![0].toUpperCase().toString() + ageData.name!!.drop(1)
            Text(
                text = "There are ${ageData.count} people named ${formattedName}!",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 10.dp, end = 10.dp),
                style = TextStyle(lineHeight = 32.sp),
                color = Color.White
            )
        }
        else{
            Text(
                text = "Type in a name to see how many people have that name",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 10.dp, end = 10.dp),
                style = TextStyle(lineHeight = 32.sp),
                color = Color.White
            )
        }
        SaveDataButton(
            onSaveClick = {viewModel.saveAgeData(ageData.count, ageData.name, ageData.age)},
            modifier = Modifier.padding(top = 30.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeSearchField(
    searchText: String,
    onChangeSearchText: (String) -> Unit,
    getAge: (String) -> Unit,
    clearText: () -> Unit,
    modifier: Modifier
){
    OutlinedTextField(
        value = searchText,
        onValueChange = { onChangeSearchText(it) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "search icon",
                modifier = Modifier
                    .clickable {
                        getAge(searchText)
                    },
                tint = Color.White
            )
        },
        trailingIcon = {
            if(searchText.isNotEmpty()) {
                Icon(
                    painter = painterResource(R.drawable.baseline_clear_24),
                    contentDescription = "clear icon",
                    modifier = Modifier
                        .clickable {
                            clearText()
                        },
                    tint = Color.White
                )
            }
        },
        modifier = modifier,
        textStyle = TextStyle(
            fontSize = 24.sp
        ),
        singleLine = true,
        shape = RoundedCornerShape(25.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.White,
            focusedBorderColor = Color.White,
            textColor = Color.White,
            cursorColor = Color.White
        )
    )
}

@Composable
fun SaveDataButton(
    onSaveClick: () -> Unit,
    modifier: Modifier
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ){
        Button(
            onClick = {
                onSaveClick()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFFFFF),
                contentColor = Color(0xFF00D100)
            )
        ) {
            Text(
                text = "Save Data",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}