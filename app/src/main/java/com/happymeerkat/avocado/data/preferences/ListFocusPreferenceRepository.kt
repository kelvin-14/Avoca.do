package com.happymeerkat.avocado.data.preferences

import android.content.ContentValues.TAG
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class ListFocusPreferenceRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val FOCUS_INDEX = intPreferencesKey("focus_index")
    }

    suspend fun saveFocusPreference(focusIndex: Int) {
        dataStore.edit { preferences ->
            preferences[FOCUS_INDEX] = focusIndex
        }
    }

    val readFocusPreference: Flow<Int> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[FOCUS_INDEX] ?: 0
        }
}