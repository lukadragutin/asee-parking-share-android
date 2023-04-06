package hr.asee.android.template.data.local.storage.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import hr.asee.android.template.data.local.storage.ApplicationStorage
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

class ApplicationStorageImpl(private val dataStorePreferences: DataStore<Preferences>) : ApplicationStorage {

    override suspend fun containsKey(key: String): Boolean {
        return dataStorePreferences.data
            .map { preferences ->
                preferences.contains(stringPreferencesKey(key))
            }.first()
    }

    override suspend fun saveString(key: String, value: String) {
        dataStorePreferences.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun getString(key: String, default: String): String {
        return dataStorePreferences.data
            .catch {
                throw it
            }.map { preferences ->
                preferences[stringPreferencesKey(key)] ?: default
            }.first()
    }

    override suspend fun deleteValue(key: String) {
        dataStorePreferences.edit { preferences ->
            stringPreferencesKey(key).also {
                if (preferences.contains(it)) {
                    preferences.remove(it)
                }
            }
        }
    }
}
