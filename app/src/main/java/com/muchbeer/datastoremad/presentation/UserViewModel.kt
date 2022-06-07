package com.muchbeer.datastoremad.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muchbeer.datastoremad.datastore.DataStorePref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val datastorePref : DataStorePref
) : ViewModel(){

    private val _readuiState = MutableStateFlow("")
    val readuiState: StateFlow<String> = _readuiState


    fun readNameByLogs() = viewModelScope.launch {
        datastorePref.retrieveNameFlow().collectLatest {
            _readuiState.value = it
            Log.d("UserViewModel", "The value from Flow is : ${_readuiState.value}")
        }
    }

    fun readNameValue() =  viewModelScope.launch {
        Log.d("UserViewModel", "The value of String : ${datastorePref.retrieveName()}")
    }

    fun saveNames(name : String )  = viewModelScope.launch {
        datastorePref.saveName(name)
    }

    fun clearValues() = viewModelScope.launch {
        datastorePref.deleteAllValues()
    }
}