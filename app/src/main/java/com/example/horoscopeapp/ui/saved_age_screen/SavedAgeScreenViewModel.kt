package com.example.horoscopeapp.ui.saved_age_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscopeapp.data.database.SavedAge
import com.example.horoscopeapp.data.database.SavedAgeDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedAgeScreenViewModel @Inject constructor(
    private val database: SavedAgeDatabase
): ViewModel() {
    var savedAgeData = MutableLiveData<List<SavedAge>>()

    fun deleteSavedAge(savedAge: SavedAge){
        viewModelScope.launch{
            database.savedAgeDAO().deleteSavedAge(savedAge)
        }
    }

    fun updateSavedAgeData(){
        viewModelScope.launch{ savedAgeData.value = database.savedAgeDAO().getAllAges() }
    }

    init {
        viewModelScope.launch{ savedAgeData.value = database.savedAgeDAO().getAllAges() }
    }
}