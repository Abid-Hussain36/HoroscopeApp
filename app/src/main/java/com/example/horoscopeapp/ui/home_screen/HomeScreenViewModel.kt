package com.example.horoscopeapp.ui.home_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscopeapp.data.AgeService
import com.example.horoscopeapp.data.dto.Age
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val service: AgeService
): ViewModel() {
    val ageData = MutableLiveData<Age>()
    val searchText = MutableLiveData<String>()

    fun getAge(name: String){
        viewModelScope.launch {
            ageData.value = service.getAge(name)
        }
    }

    fun onSearchTextChanged(newText: String){
        searchText.value = newText
    }

    fun clearSearchText(){
        searchText.value = ""
    }
}