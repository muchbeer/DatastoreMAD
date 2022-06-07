package com.muchbeer.datastoremad.repository

interface DataStoreRepository {
    fun saveData(data: String)
    fun retrieveData()
}