package com.muchbeer.datastoremad.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.muchbeer.datastoremad.utils.PostConstant
import com.muchbeer.datastoremad.utils.PostConstant.USER_NAMES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStorePrefImpl @Inject constructor(private val dataStore: DataStore<Preferences>):
    DataStorePref {


   private suspend fun saveUsername(username : String ) {
       dataStore.edit { preferences->
            preferences[USER_NAMES] = username
       }
   }

  private fun readuserNameFlow() : Flow<String> {
      val preferenc =  dataStore.data
          .catch { exception ->
              if (exception is IOException) {
                  emit(emptyPreferences())
              } else {
                  throw exception
              }
          }.
      map { preferences ->
          preferences[USER_NAMES] ?: "muchbeer"
      }

        return preferenc
  }

    private suspend fun deleteValue() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    override suspend fun saveName(name: String) {  saveUsername(name)   }

    override fun retrieveNameFlow(): Flow<String> = readuserNameFlow()

    override suspend fun retrieveName(): String {
        val preferenc = dataStore.data.first()
        return preferenc[USER_NAMES] ?: "muchbeer"
    }

    override suspend fun deleteAllValues() {
       deleteValue()
    }
}