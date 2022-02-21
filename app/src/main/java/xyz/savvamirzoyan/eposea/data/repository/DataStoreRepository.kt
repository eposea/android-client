package xyz.savvamirzoyan.eposea.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

interface DataStoreRepository {

    suspend fun fetchToken(): String
    suspend fun saveToken(token: String)

    class Base(
        private val dataStore: DataStore<Preferences>
    ) : DataStoreRepository {

        override suspend fun fetchToken() = dataStore.data.first()[USER_TOKEN] ?: ""

        override suspend fun saveToken(token: String) {
            dataStore.edit { settings ->
                settings[USER_TOKEN] = token
            }
        }

        private companion object {
            val USER_TOKEN = stringPreferencesKey("token")
        }
    }
}