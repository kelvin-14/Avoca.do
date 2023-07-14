package com.happymeerkat.avocado.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val LIST_FOCUS_PREFERENCE_NAME = "focus_preference"
val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    // just my preference of naming including the package name
    name = LIST_FOCUS_PREFERENCE_NAME
)