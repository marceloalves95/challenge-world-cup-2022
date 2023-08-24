package br.com.challenge_world_cup_2022.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import br.com.challenge_world_cup_2022.data.source.DataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataSourceLocalImpl(
    private val dataStore: DataStore<Preferences>
) : DataSource.Local {

    private val key = stringSetPreferencesKey("notification_ids")

    override fun getActiveNotificationIds(): Flow<Set<String>> =
        dataStore.data
            .map { preferences ->
                preferences[key] ?: setOf()
            }

    override suspend fun enableNotificationFor(id: String) {
        dataStore.edit { settings ->
            val currentIds = settings[key] ?: setOf()
            settings[key] = currentIds + id
        }
    }

    override suspend fun disableNotificationFor(id: String) {
        dataStore.edit { settings ->
            val currentIds = settings[key] ?: return@edit
            settings[key] = currentIds - id
        }
    }
}