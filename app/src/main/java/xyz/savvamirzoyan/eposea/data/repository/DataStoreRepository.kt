package xyz.savvamirzoyan.eposea.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import java.io.IOException

interface DataStoreRepository {

    interface Token : DataStoreRepository {
        suspend fun fetchToken(): String?
        suspend fun saveToken(token: String)
    }

    class TokenDataStoreRepository(
        private val dataStore: DataStore<Preferences>
    ) : Token {

        override suspend fun fetchToken() = try {
            dataStore.data.first()[USER_TOKEN]
        } catch (e: IOException) {
            null
        }

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