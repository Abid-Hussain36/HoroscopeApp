package com.example.horoscopeapp.ui.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
            Text(
                text = "You typed in ${ageData.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            )
        }
        else{
            Text(
                text = "Type in a name to see some cool stuff!",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        if(ageData.age != null){
            Text(
                text = "The average age for someone with this name is ${ageData.age.toString()} years old!",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        else{
            Text(
                text = "Type in a name to see what its average age will be",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        if(ageData.count != null){
            Text(
                text = "There are ${ageData.count} people named ${ageData.name} on the planet!",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        else{
            Text(
                text = "Type in a name to see how many people have that name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        SaveDataButton(
            onSaveClick = {viewModel.saveAgeData(ageData.count, ageData.name, ageData.age)},
            modifier = Modifier.padding(top = 16.dp)
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
                    }
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
                        }
                )
            }
        },
        modifier = modifier
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
        ) {
            Text(text = "Save Data")
        }
    }
}