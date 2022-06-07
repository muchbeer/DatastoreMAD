package com.muchbeer.datastoremad.datastore

import kotlinx.coroutines.flow.Flow

interface DataStorePref {
   suspend fun saveName(name : String)
    fun retrieveNameFlow() : Flow<String>
    suspend fun retrieveName() : String
    suspend fun deleteAllValues()
}