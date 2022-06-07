package com.muchbeer.datastoremad.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.muchbeer.datastoremad.datastore.DataStorePref
import com.muchbeer.datastoremad.datastore.DataStorePrefImpl
import com.muchbeer.datastoremad.utils.PostConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatastoreModule {

    @Singleton
    @Provides
    fun provideDatastoreDI(@ApplicationContext appContext: Context) :
            DataStore<Preferences> {
return PreferenceDataStoreFactory.create(
    corruptionHandler = ReplaceFileCorruptionHandler(
        produceNewData = { emptyPreferences() }
    ),
    scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
    produceFile = { appContext.preferencesDataStoreFile(PostConstant.USER_PREFERENCES_NAME)}
)
    }

    @Singleton
    @Provides
    fun provideDataStorePref(dataStore : DataStore<Preferences>) : DataStorePref{
        return DataStorePrefImpl(dataStore)
    }
}